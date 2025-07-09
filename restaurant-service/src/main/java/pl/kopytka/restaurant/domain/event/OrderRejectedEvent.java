package pl.kopytka.restaurant.domain.event;

import lombok.Builder;
import lombok.Getter;
import pl.kopytka.common.kafka.DomainEvent;

import java.util.UUID;

@Getter
@Builder
public class OrderRejectedEvent implements DomainEvent {
    private final UUID restaurantId;
    private final UUID orderId;
    private final String rejectionReason;
}
