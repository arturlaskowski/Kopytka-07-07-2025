package pl.kopytka.common.kafka;


public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(T domainEvent);
}
