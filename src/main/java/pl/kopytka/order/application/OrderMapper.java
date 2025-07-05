package pl.kopytka.order.application;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.kopytka.order.application.dto.CreateOrderAddressDto;
import pl.kopytka.order.application.dto.CreateOrderItemDto;
import pl.kopytka.order.application.dto.OrderAddressDto;
import pl.kopytka.order.application.dto.OrderItemDto;
import pl.kopytka.order.domain.Money;
import pl.kopytka.order.domain.OrderAddress;
import pl.kopytka.order.domain.OrderItem;
import pl.kopytka.order.domain.Quantity;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "price", source = "price", qualifiedByName = "toMoney")
    @Mapping(target = "quantity", source = "quantity", qualifiedByName = "toQuantity")
    @Mapping(target = "totalPrice", source = "totalPrice", qualifiedByName = "toMoney")
    OrderItem toOrderItem(CreateOrderItemDto itemDto);
    
    List<OrderItem> toOrderItems(List<CreateOrderItemDto> itemDtos);

    OrderAddress toOrderAddress(CreateOrderAddressDto addressDto);

    @Mapping(target = "quantity", source = "quantity.value")
    @Mapping(target = "price", source = "price.amount")
    @Mapping(target = "totalPrice", source = "totalPrice.amount")
    OrderItemDto toOrderItemDto(OrderItem item);
    
    List<OrderItemDto> toOrderItemDtos(List<OrderItem> basketItems);

    OrderAddressDto toOrderAddressDto(OrderAddress orderAddress);
    
    @Named("toMoney")
    default Money toMoney(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }
    
    @Named("toQuantity")
    default Quantity toQuantity(int value) {
        return new Quantity(value);
    }
}