package pl.kopytka.customer.application.exception;

import pl.kopytka.common.domain.valueobject.CustomerId;

public class WalletCreationFailedException extends RuntimeException {
    public WalletCreationFailedException(CustomerId customerId) {
        super("Failed to create wallet for customer with ID: " + customerId.id());
    }
}
