package pl.kopytka.restaurant.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApproveOrderRequest {
    private UUID orderId;
    private UUID restaurantId;
    private UUID productId;
}
