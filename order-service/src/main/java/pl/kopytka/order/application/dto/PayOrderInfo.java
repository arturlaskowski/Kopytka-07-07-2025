package pl.kopytka.order.application.dto;

import pl.kopytka.order.domain.OrderStatus;

import java.time.Instant;
import java.util.UUID;

public record PayOrderInfo(
        UUID orderId,
        OrderStatus status,
        String message,
        Instant timestamp
) {
    public static PayOrderInfo success(UUID orderId) {
        return new PayOrderInfo(
                orderId,
                OrderStatus.PAID,
                "Payment successful",
                Instant.now()
        );
    }

    public static PayOrderInfo failed(UUID orderId, String errorMessage) {
        return new PayOrderInfo(
                orderId,
                OrderStatus.FAILED,
                "Payment failed: " + errorMessage,
                Instant.now()
        );
    }
}
