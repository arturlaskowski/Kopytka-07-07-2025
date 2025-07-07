package pl.kopytka.order.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.kopytka.avro.payment.ProcessPaymentCommandAvroModel;
import pl.kopytka.common.kafka.DomainEventPublisher;
import pl.kopytka.common.kafka.config.producer.KafkaProducer;
import pl.kopytka.order.domain.event.OrderPaymentEvent;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class ProcessPaymentCommandPublisher implements DomainEventPublisher<OrderPaymentEvent> {

    private final TopicsConfigData topicsConfigData;
    private final KafkaProducer<String, ProcessPaymentCommandAvroModel> kafkaProducer;

    @Override
    public void publish(OrderPaymentEvent domainEvent) {
        var orderId = domainEvent.getOrder().getId().id();
        var customerId = domainEvent.getOrder().getCustomerId().id();
        var price = domainEvent.getOrder().getPrice().amount();

        var processPaymentCommandAvroModel = new ProcessPaymentCommandAvroModel(
                orderId,
                customerId,
                price,
                Instant.now()
        );

        kafkaProducer.send(topicsConfigData.getPaymentCommand(),
                orderId.toString(),
                processPaymentCommandAvroModel
        );
    }
}
