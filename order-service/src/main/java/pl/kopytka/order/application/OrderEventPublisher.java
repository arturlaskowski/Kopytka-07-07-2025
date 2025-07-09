package pl.kopytka.order.application;


import pl.kopytka.common.kafka.DomainEventPublisher;
import pl.kopytka.order.domain.event.OrderEvent;

public interface OrderEventPublisher extends DomainEventPublisher<OrderEvent> {
    void publish(OrderEvent event);
}
