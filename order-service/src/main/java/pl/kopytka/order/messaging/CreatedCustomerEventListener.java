package pl.kopytka.order.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.kopytka.avro.customer.CustomerEventAvroModel;
import pl.kopytka.common.kafka.config.consumer.AbstractKafkaConsumer;
import pl.kopytka.order.application.replicaiton.CustomerView;
import pl.kopytka.order.application.replicaiton.CustomerViewService;

import java.util.List;

@Component
@RequiredArgsConstructor
class CreatedCustomerEventListener extends AbstractKafkaConsumer<CustomerEventAvroModel> {

    private final CustomerViewService customerViewService;

    @Override
    @KafkaListener(id = "CreatedCustomerEventListener",
            groupId = "${order-service.kafka.group-id}",
            topics = "${order-service.kafka.topics.customer-event}")
    protected void processMessages(List<CustomerEventAvroModel> messages) {
        messages.forEach(event ->
                customerViewService.onCreateCustomer(new CustomerView(event.getCustomerId())));
    }

    @Override
    protected String getMessageTypeName() {
        return "customerEvent";
    }
}