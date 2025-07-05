package pl.kopytka.order.application.integration.customer;

import pl.kopytka.common.domain.valueobject.CustomerId;

public class CustomerServiceUnavailableException extends RuntimeException {

    public CustomerServiceUnavailableException(CustomerId customerId, Throwable cause) {
        super("Customer service is unavailable while verifying customer: " + customerId.id(), cause);
    }
}
