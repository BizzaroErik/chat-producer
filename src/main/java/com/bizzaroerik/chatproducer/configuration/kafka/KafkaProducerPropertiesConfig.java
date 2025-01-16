package com.bizzaroerik.chatproducer.configuration.kafka;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class KafkaProducerPropertiesConfig {
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

//    @NonNull
//    @Value("${spring.kafka.properties.security.protocol}")
//    String securityProtocol;
//
//    @NonNull
//    @Value("${spring.kafka.properties.sasl.mechanism}")
//    String saslMechanism;
//
//    @NonNull
//    @Value("${spring.kafka.properties.sasl.jaas.config}")
//    String jaasConfig;

}
