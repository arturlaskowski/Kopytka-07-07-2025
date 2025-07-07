package pl.kopytka.payment.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.kopytka.avro.customer.CustomerEventAvroModel;
import pl.kopytka.common.domain.valueobject.CustomerId;
import pl.kopytka.common.domain.valueobject.Money;
import pl.kopytka.common.kafka.config.consumer.AbstractKafkaConsumer;
import pl.kopytka.payment.application.WalletService;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
class CreatedCustomerEventListener extends AbstractKafkaConsumer<CustomerEventAvroModel> {

    private final WalletService walletService;

    @Override
    @KafkaListener(id = "CreatedCustomerEventListener",
            groupId = "${payment-service.kafka.group-id}",
            topics = "${payment-service.kafka.topics.customer-event}")
    protected void processMessages(List<CustomerEventAvroModel> messages) {
        messages.forEach(event ->
                walletService.createWallet(new CustomerId(event.getCustomerId()), Money.ZERO));
    }

    @Override
    protected String getMessageTypeName() {
        return "customerEvent";
    }
}
