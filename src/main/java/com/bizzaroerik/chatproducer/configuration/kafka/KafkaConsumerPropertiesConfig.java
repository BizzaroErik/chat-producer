package com.bizzaroerik.chatproducer.configuration.kafka;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 */
@Getter
@Component
public class KafkaConsumerPropertiesConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    String bootstrapServers;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    String autoOffsetReset;

    @Value("${spring.kafka.consumer.key-deserializer}")
    String keyDeserializer;

    @Value("${spring.kafka.consumer.value-deserializer}")
    String valueDeserializer;

    @Value("${spring.kafka.consumer.isolation-level}")
    String isolationLevel;

    @Value("${spring.kafka.consumer.max-poll-records}")
    String maxPollRecords;

    @Value("${spring.kafka.consumer.session-time-out-ms}")
    String sessionTimeoutMs;

    @Value("${spring.kafka.consumer.heartbeat-interval-ms}")
    String heartbeatIntervalMs;

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
