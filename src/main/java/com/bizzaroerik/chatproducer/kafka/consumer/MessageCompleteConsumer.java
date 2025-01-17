package com.bizzaroerik.chatproducer.kafka.consumer;


import com.bizzaroerik.chatproducer.exception.ValueDeserializationException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Message Consumer
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageCompleteConsumer {

    @KafkaListener(containerFactory = "cloudEventListenerContainerFactory",
            groupId = "${spring.kafka.listener.chat-success-consumer.groupId}",
            clientIdPrefix = "${spring.kafka.consumer.client-id}",
            topics = {"${spring.kafka.listener.chat-success-consumer.topic}"},
            concurrency = "${spring.kafka.consumer.concurrency:1}")
    public void messageCompleteConsumer(final ConsumerRecord<String, JsonNode> consumerRecord) throws ValueDeserializationException {
        log.debug("Successfully consumed message");
    }
}
