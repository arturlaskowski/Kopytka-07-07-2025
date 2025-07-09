package pl.kopytka.restaurant.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.kopytka.avro.restaurant.RestaurantApproveOrderAvroCommand;
import pl.kopytka.avro.restaurant.RestaurantOrderCommandAvroModel;
import pl.kopytka.common.kafka.config.consumer.AbstractKafkaConsumer;
import pl.kopytka.restaurant.application.RestaurantOrderApplicationService;
import pl.kopytka.restaurant.application.dto.ProductDto;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
class RestaurantOrderCommandListener extends AbstractKafkaConsumer<RestaurantOrderCommandAvroModel> {

    private final RestaurantOrderApplicationService restaurantOrderApplicationService;

    @Override
    @KafkaListener(id = "RestaurantOrderCommandListener",
            groupId = "${restaurant-service.kafka.group-id}",
            topics = "${restaurant-service.kafka.topics.restaurant-order-command}")
    protected void processMessages(List<RestaurantOrderCommandAvroModel> messages) {
        messages.forEach(command -> {
            switch (command.getType()) {
                case APPROVE_ORDER -> processRestaurantApprovalCommand(command);
                default -> log.warn("Unknown payment command type: {}", command.getType());
            }
        });
    }

    private void processRestaurantApprovalCommand(RestaurantOrderCommandAvroModel command) {
        var avroCommand = (RestaurantApproveOrderAvroCommand) command.getPayload();

        var products = avroCommand.getProducts().stream()
                .map(product -> new ProductDto(product.getId(), product.getQuantity()))
                .toList();

        restaurantOrderApplicationService.approveOrder(
                avroCommand.getRestaurantId(),
                avroCommand.getOrderId(),
                products
        );
    }

    @Override
    protected String getMessageTypeName() {
        return "restaurantOrderCommand";
    }
}
