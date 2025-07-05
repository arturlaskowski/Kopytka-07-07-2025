package pl.kopytka.common.web.dto;

import java.util.UUID;

public record PaymentResultResponse(
        UUID paymentId,
        boolean success,
        String errorMessage
) {
}
