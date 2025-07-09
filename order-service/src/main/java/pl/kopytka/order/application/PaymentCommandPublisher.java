package pl.kopytka.order.application;

import pl.kopytka.common.domain.valueobject.CustomerId;
import pl.kopytka.common.domain.valueobject.Money;
import pl.kopytka.common.domain.valueobject.OrderId;

public interface PaymentCommandPublisher {

    void publishSubtractPointsCommand(OrderId orderId, CustomerId customerId, Money amount);

}