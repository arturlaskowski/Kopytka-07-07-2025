package pl.kopytka.payment.application.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class PaymentCommand {
    private UUID orderId;
    private UUID customerId;
    private BigDecimal amount;
    private String description;
}
