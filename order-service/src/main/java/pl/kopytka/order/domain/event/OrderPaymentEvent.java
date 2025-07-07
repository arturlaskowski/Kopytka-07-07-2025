package pl.kopytka.order.domain.event;

import pl.kopytka.order.domain.Order;

public class OrderPaymentEvent extends OrderEvent {
    public OrderPaymentEvent(Order order) {
        super(order);
    }
}
