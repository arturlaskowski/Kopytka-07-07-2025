package pl.kopytka.customer;

import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "Could not find customer with customerId: %s";

    public static String createExceptionMessage(UUID id) {
        return String.format(MESSAGE_TEMPLATE, id);
    }

    public CustomerNotFoundException(final UUID id) {
        super(createExceptionMessage(id));
    }
}