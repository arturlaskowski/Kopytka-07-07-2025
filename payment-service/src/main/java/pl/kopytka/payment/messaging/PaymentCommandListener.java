package pl.kopytka.payment.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.kopytka.avro.payment.ProcessPaymentCommandAvroModel;
import pl.kopytka.common.kafka.config.consumer.AbstractKafkaConsumer;
import pl.kopytka.payment.application.PaymentApplicationService;
import pl.kopytka.payment.application.dto.MakePaymentCommand;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
class PaymentCommandListener extends AbstractKafkaConsumer<ProcessPaymentCommandAvroModel> {

    private final PaymentApplicationService paymentApplicationService;

    @Override
    @KafkaListener(id = "PaymentCommandListener",
            groupId = "${payment-service.kafka.group-id}",
            topics = "${payment-service.kafka.topics.payment-command}")
    protected void processMessages(List<ProcessPaymentCommandAvroModel> messages) {
        messages.forEach(message -> {
            var makePaymentCommand = new MakePaymentCommand(message.getOrderId(),
                    message.getCustomerId(),
                    message.getPrice());
            paymentApplicationService.makePayment(makePaymentCommand);
        });
    }

    @Override
    protected String getMessageTypeName() {
        return "paymentCommand";
    }
}
