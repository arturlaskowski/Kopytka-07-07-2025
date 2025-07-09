package pl.kopytka.restaurant.application.dto;

import java.util.UUID;

public record ApproveOrderCommand(
    UUID orderId,
    UUID restaurantId,
    UUID productId
) {}
