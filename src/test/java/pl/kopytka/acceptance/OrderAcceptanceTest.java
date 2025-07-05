
package pl.kopytka.acceptance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.kopytka.customer.Customer;
import pl.kopytka.common.CustomerId;
import pl.kopytka.customer.CustomerRepository;
import pl.kopytka.order.application.OrderRepository;
import pl.kopytka.order.application.dto.CreateOrderAddressDto;
import pl.kopytka.order.application.dto.CreateOrderDto;
import pl.kopytka.order.application.dto.CreateOrderItemDto;
import pl.kopytka.order.domain.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderAcceptanceTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("""
            given request to add order for existing customer,
            when request is sent,
            then save order and HTTP 200 status received""")
    void givenRequestToAddOrderForExistingCustomer_whenRequestIsSent_thenOrderSavedAndHttp200() {
        // given
        var createOrderDto = createOrderDto();

        // when
        ResponseEntity<Void> response = restTemplate.postForEntity(getBaseUrl(), createOrderDto, Void.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getHeaders().getLocation()).isNotNull();
        var orderId = response.getHeaders().getLocation().getPath().split("/")[3];

        var savedOrder = orderRepository.findById(new OrderId(UUID.fromString(orderId))).orElseThrow();
        assertThat(savedOrder)
                .hasNoNullFieldsOrProperties()
                .hasFieldOrPropertyWithValue("customerId", new CustomerId(createOrderDto.customerId()))
                .hasFieldOrPropertyWithValue("price", new Money(createOrderDto.price()))
                .hasFieldOrPropertyWithValue("status", OrderStatus.PENDING)
                .extracting(Order::getAddress)
                .hasFieldOrPropertyWithValue("street", createOrderDto.address().street())
                .hasFieldOrPropertyWithValue("postalCode", createOrderDto.address().postalCode())
                .hasFieldOrPropertyWithValue("city", createOrderDto.address().city())
                .hasFieldOrPropertyWithValue("houseNo", createOrderDto.address().houseNo());

        assertThat(savedOrder.getItems()).hasSize(createOrderDto.basketItems().size())
                .zipSatisfy(createOrderDto.basketItems(), (orderItem, orderItemDto) -> {
                    assertThat(orderItem.getProductId()).isEqualTo(orderItemDto.productId());
                    assertThat(orderItem.getPrice()).isEqualTo(new Money(orderItemDto.price()));
                    assertThat(orderItem.getQuantity()).isEqualTo(new Quantity(orderItemDto.quantity()));
                    assertThat(orderItem.getTotalPrice()).isEqualTo(new Money(orderItemDto.totalPrice()));
                });
    }

    private CreateOrderDto createOrderDto() {
        var customerId = customerRepository.save(new Customer("Waldek", "Kiepski", "waldek@gmail.com")).getCustomerId().id();

        var items = List.of(new CreateOrderItemDto(UUID.randomUUID(), 2, new BigDecimal("10.00"), new BigDecimal("20.00")),
                new CreateOrderItemDto(UUID.randomUUID(), 1, new BigDecimal("34.56"), new BigDecimal("34.56")));
        var address = new CreateOrderAddressDto("Małysza", "94-000", "Adasiowo", "12");
        return new CreateOrderDto(customerId, new BigDecimal("54.56"), items, address);
    }

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/orders";
    }
}
