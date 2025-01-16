package com.bizzaroerik.chatproducer.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageProducer {
/*

    @NonNull
    @Value("${topic.chat-topic}")
    private final String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final CustomProducerListener producerListener;

    */
/**
 * Method to send messages to a topic
 *
 * @param data : Content to be sent to the topic
 *//*

    public void send(final String userId, final String data) {
        kafkaTemplate.executeInTransaction(t -> {
            t.send(topicName, "chat-" + userId + "-" + data, data);
            return true;
        });
    }
*/

}
