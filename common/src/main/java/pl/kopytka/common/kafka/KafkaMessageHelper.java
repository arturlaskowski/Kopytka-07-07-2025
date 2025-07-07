package pl.kopytka.common.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Slf4j
@Component
public class KafkaMessageHelper {

    public <T> BiConsumer<SendResult<String, T>, Throwable>
    getKafkaCallback(String responseTopicName, T avroModel, String resourceId) {
        return (result, ex) -> {
            if (ex != null) {
                // Handle failure
                log.error("Error while sending message for resource id: {} with payload: {} to topic: {}",
                        resourceId, avroModel.toString(), responseTopicName, ex);
            } else {
                // Handle success
                RecordMetadata metadata = result.getRecordMetadata();
                log.info("Received successful response from Kafka for resource id: {}. " +
                                "Topic: {} Partition: {} Offset: {} Timestamp: {}",
                        resourceId,
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp());
            }
        };
    }
}