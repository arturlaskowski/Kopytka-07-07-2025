package pl.kopytka.order.application.exception;

import java.util.UUID;

public class InvalidOrderException extends RuntimeException {

    public static String createExceptionMessage(UUID id) {
        return String.format("Could not find customer with customerId: %s", id);
    }

    public InvalidOrderException(final UUID id) {
        super(createExceptionMessage(id));
    }
}