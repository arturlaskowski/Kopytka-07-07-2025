package pl.kopytka.order.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class BasketItemId implements Serializable {

    private Integer itemNumber;
    private Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketItemId that = (BasketItemId) o;
        return Objects.equals(itemNumber, that.itemNumber) &&
               Objects.equals(order != null ? order.getId() : null,
                             that.order != null ? that.order.getId() : null);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemNumber, order != null ? order.getId() : null);
    }
}
