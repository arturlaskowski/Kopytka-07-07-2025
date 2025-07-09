package pl.kopytka.order.saga;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

interface OrderSagaRepository extends CrudRepository<OrderSaga, UUID> {

    Optional<OrderSaga> findByOrderId(UUID id);
}
