package pl.kopytka.trackorder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Builder
@Table(name = "tracking_order")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TrackingOrderProjection {

    @Id
    private UUID orderId;

    private String status;

    @Column(name = "price")
    private BigDecimal amount;
}
