package com.bizzaroerik.chatproducer.kafka.producer;

import com.bizzaroerik.chatproducer.domain.MessageRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageProducer {

    @NotNull
    @Value("${topic.chat-topic}")
    private final String topicName;

    private final KafkaTemplate<String, MessageRequest> kafkaTemplate;

    private final CustomProducerListener producerListener;

    public void send(final MessageRequest message) {
        kafkaTemplate.executeInTransaction(t -> {
            String key = "chat-" + message.getUserId() + "-" + message.getVolume();
            ProducerRecord<String, MessageRequest> record = new ProducerRecord<>(topicName, key, message);
            t.send(record);
            return true;
        });
    }

}
