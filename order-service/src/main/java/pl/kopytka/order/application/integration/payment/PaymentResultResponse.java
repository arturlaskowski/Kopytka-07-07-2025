package pl.kopytka.order.application.integration.payment;

import java.util.UUID;

public record PaymentResultResponse(
        UUID paymentId,
        boolean success,
        String errorMessage
) {
}
