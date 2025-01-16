package com.bizzaroerik.chatproducer.kafka.consumer;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Message Consumer
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageCompleteConsumer {

/*    @KafkaListener(containerFactory = "cloudEventListenerContainerFactory",
            groupId = "${spring.kafka.listener.chat-success-consumer.groupId}",
            clientIdPrefix = "${spring.kafka.consumer.client-id}",
            topics = {"${spring.kafka.listener.chat-success-consumer.topic}"},
            concurrency = "${spring.kafka.consumer.concurrency:1}")
    public void messageCompleteConsumer(final ConsumerRecord<String, CloudEvent> consumerRecord) throws ValueDeserializationException {
        log.debug("Successfully consumed message");
    }*/
}
