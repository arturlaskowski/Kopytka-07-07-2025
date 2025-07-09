package pl.kopytka.order.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.kopytka.common.domain.valueobject.CustomerId;
import pl.kopytka.common.domain.valueobject.Money;
import pl.kopytka.common.domain.valueobject.OrderId;
import pl.kopytka.order.application.PaymentCommandPublisher;
import pl.kopytka.order.saga.OrderSagaDispatcher;

@Component
@RequiredArgsConstructor
public class KafkaPaymentCommandPublisher implements PaymentCommandPublisher {

    private final OrderSagaDispatcher orderSagaDispatcher;

    @Override
    public void publishSubtractPointsCommand(OrderId orderId, CustomerId customerId, Money amount) {
        orderSagaDispatcher.start(orderId, customerId, amount);
    }
}
