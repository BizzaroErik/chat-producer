package com.bizzaroerik.chatproducer.configuration.kafka;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * Kafka producer configs loaded from application properties
 */
@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {

    @NonNull
    private final KafkaProducerPropertiesConfig properties;

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() throws UnknownHostException {
        return new KafkaTemplate<>(producerFactory());
    }

    /**
     * @return
     */
    private DefaultKafkaProducerFactory<String, String> producerFactory() throws UnknownHostException {
        Map<String, Object> configProps = new HashMap<>();
        final String hostName = InetAddress.getLocalHost().getHostName();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        configProps.put(ProducerConfig.CLIENT_ID_CONFIG, String.format("%s_%s", properties.getClientId(), hostName));
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, properties.getKeySerializer());
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, properties.getValueSerializer());
        configProps.put(ProducerConfig.ACKS_CONFIG, properties.getAcks());
        configProps.put(ProducerConfig.RETRIES_CONFIG, properties.getRetries());
        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, properties.getBatchSize());
        configProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG, properties.getBufferMemory());
        configProps.put(ProducerConfig.LINGER_MS_CONFIG, properties.getLingerMs());
        configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, properties.getEnableIdempotence());
        configProps.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, properties.getTransactionalId());
//        configProps.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, properties.getSecurityProtocol());
//        configProps.put("sasl.mechanism", properties.getSaslMechanism());
//        configProps.put("sasl.jaas.config", properties.getJaasConfig());
        DefaultKafkaProducerFactory<String, String> factory = new DefaultKafkaProducerFactory<>(configProps);
        if (properties.getTransactionalId() != null) {
            factory.setTransactionIdPrefix(properties.getTransactionalId() + "_" + InetAddress.getLocalHost().getHostName());
            //factory.setProducerPerConsumerPartition(false);
        }
        return factory;
    }
}
