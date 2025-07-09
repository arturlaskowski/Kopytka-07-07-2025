package pl.kopytka.payment.application;


import pl.kopytka.common.kafka.DomainEventPublisher;
import pl.kopytka.payment.domain.event.PaymentEvent;

public interface PaymentEventPublisher extends DomainEventPublisher<PaymentEvent>  {
    void publish(PaymentEvent event);
}
