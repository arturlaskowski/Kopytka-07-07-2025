package pl.kopytka.restaurant.application.dto;

import java.util.UUID;

public record OrderApprovalResult(
    UUID orderId,
    boolean approved,
    String message
) {}
