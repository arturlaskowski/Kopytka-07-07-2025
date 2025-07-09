package pl.kopytka.restaurant.application;


import pl.kopytka.common.kafka.DomainEvent;

public interface RestaurantOrderEventPublisher {
    void publish(DomainEvent event);
}
