package com.bizzaroerik.chatproducer.configuration.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka consumer configuration loaded from application properties
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {

    @NonNull
    private final KafkaConsumerPropertiesConfig properties;

    /**
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, JsonNode> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, JsonNode> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
        factory.getContainerProperties().setSyncCommits(true);
        factory.setRecordFilterStrategy(consumerRecord -> !(consumerRecord.key().contains("chat-")));
        return factory;
    }

    @Bean(name = "cloudEventListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, JsonNode> cloudEventListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, JsonNode> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(cloudEventConsumerFactory());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
        factory.getContainerProperties().setSyncCommits(true);
        //factory.setRecordFilterStrategy(cloudEvent -> !(cloudEvent.value().getType().equals("MESSAGE_EVENT")));
        return factory;
    }

    private DefaultKafkaConsumerFactory<String, JsonNode> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, properties.getAutoOffsetReset());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, properties.getHeartbeatIntervalMs());
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, properties.getSessionTimeoutMs());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, properties.getMaxPollRecords());
        props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, properties.getIsolationLevel());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, properties.getKeyDeserializer());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, properties.getValueDeserializer());
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, ValueDeserializer.class);
/*        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, properties.getSecurityProtocol());
        props.put("sasl.mechanism", properties.getSaslMechanism());
        props.put("sasl.jaas.config", properties.getJaasConfig());*/
        return new DefaultKafkaConsumerFactory<>(props);
    }

    private DefaultKafkaConsumerFactory<String, JsonNode> cloudEventConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, properties.getAutoOffsetReset());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, properties.getHeartbeatIntervalMs());
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, properties.getSessionTimeoutMs());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, properties.getMaxPollRecords());
        props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, properties.getIsolationLevel());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, properties.getKeyDeserializer());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, properties.getValueDeserializer());
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, ValueDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_FUNCTION, DeserializationErrorHandler.class);
/*        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, properties.getSecurityProtocol());
        props.put("sasl.mechanism", properties.getSaslMechanism());
        props.put("sasl.jaas.config", properties.getJaasConfig());*/
        return new DefaultKafkaConsumerFactory<>(props);
    }
}
