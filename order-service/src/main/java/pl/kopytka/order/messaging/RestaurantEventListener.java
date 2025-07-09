package pl.kopytka.order.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.kopytka.avro.restaurant.RestaurantOrderApprovedAvroEvent;
import pl.kopytka.avro.restaurant.RestaurantOrderEventAvroModel;
import pl.kopytka.avro.restaurant.RestaurantOrderRejectedAvroEvent;
import pl.kopytka.common.domain.valueobject.OrderId;
import pl.kopytka.common.kafka.config.consumer.AbstractKafkaConsumer;
import pl.kopytka.order.application.OrderApplicationService;

import java.util.List;

@Component
@RequiredArgsConstructor
class RestaurantEventListener extends AbstractKafkaConsumer<RestaurantOrderEventAvroModel> {

    private final OrderApplicationService orderApplicationService;

    @Override
    @KafkaListener(id = "RestaurantEventListener",
            groupId = "${order-service.kafka.group-id}",
            topics = "${order-service.kafka.topics.restaurant-order-event}")
    protected void processMessages(List<RestaurantOrderEventAvroModel> messages) {
        messages.forEach(event -> {
            switch (event.getType()) {
                case ORDER_APPROVED ->
                        handleRestaurantOrderApproved(((RestaurantOrderApprovedAvroEvent) event.getPayload()));
                case ORDER_REJECTED ->
                        handleRestaurantOrderRejected((RestaurantOrderRejectedAvroEvent) event.getPayload());
            }
        });
    }

    private void handleRestaurantOrderApproved(RestaurantOrderApprovedAvroEvent event) {
        var orderId = new OrderId(event.getOrderId());
        orderApplicationService.approveOrder(orderId);
    }

    private void handleRestaurantOrderRejected(RestaurantOrderRejectedAvroEvent event) {
        var orderId = new OrderId(event.getOrderId());
        orderApplicationService.initCancelOrder(orderId, event.getFailureMessages());
    }

    @Override
    protected String getMessageTypeName() {
        return "restaurantEvent";
    }
}