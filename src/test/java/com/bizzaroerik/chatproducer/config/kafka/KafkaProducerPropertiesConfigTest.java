package com.bizzaroerik.chatproducer.config.kafka;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class KafkaProducerPropertiesConfigTest {
    @NonNull
    @Value("${spring.kafka.producer.bootstrap-servers}")
    String bootstrapServers;

    @Value("${spring.kafka.producer.client-id}")
    String clientId;

    @NonNull
    @Value("${spring.kafka.producer.key-serializer}")
    String keySerializer;

    @NonNull
    @Value("${spring.kafka.producer.value-serializer}")
    String valueSerializer;

    @NonNull
    @Value("${spring.kafka.producer.acks}")
    String acks;

    @Value("${spring.kafka.producer.retries}")
    String retries;

    @Value("${spring.kafka.producer.batch-size}")
    String batchSize;

    @Value("${spring.kafka.producer.buffer-memory}")
    String bufferMemory;

    @Value("${spring.kafka.producer.linger-ms}")
    String lingerMs;

    @Value("${spring.kafka.producer.enable-Idempotence}")
    String enableIdempotence;

    @Value("${spring.kafka.producer.transactionalId}")
    String transactionalId;
}
