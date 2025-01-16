package com.bizzaroerik.chatproducer.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomProducerListener implements ProducerListener {

    @Override
    public void onError(ProducerRecord producerRecord, RecordMetadata recordMetadata, Exception exception) {
        log.error("Error: Failed to produce message. \nTopic: {}, \nKey: {}, \nValue: {}, \nCause: {}",
                producerRecord.topic(), producerRecord.key(), producerRecord.value(), exception.getMessage());
    }
}
