package com.bizzaroerik.chatproducer.config.kafka;

import com.bizzaroerik.chatproducer.configuration.kafka.KafkaProducerPropertiesConfig;
import com.bizzaroerik.chatproducer.domain.MessageRequest;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@TestConfiguration
public class KafkaProducerConfigTest {

    @Autowired
    KafkaProducerPropertiesConfig properties;

    @Bean
    @Primary
    public KafkaTemplate<String, MessageRequest> kafkaTemplateTest() throws UnknownHostException {
        return new KafkaTemplate<>(producerFactory());
    }

    private DefaultKafkaProducerFactory<String, MessageRequest> producerFactory() throws UnknownHostException {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        configProps.put(ProducerConfig.CLIENT_ID_CONFIG, properties.getClientId() + "_" + InetAddress.getLocalHost().getHostName());
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, properties.getKeySerializer());
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, properties.getValueSerializer());
        configProps.put(ProducerConfig.ACKS_CONFIG, properties.getAcks());
        configProps.put(ProducerConfig.RETRIES_CONFIG, properties.getRetries());
        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, properties.getBatchSize());
        configProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG, properties.getBufferMemory());
        configProps.put(ProducerConfig.LINGER_MS_CONFIG, properties.getLingerMs());
        configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, properties.getEnableIdempotence());
        configProps.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, properties.getTransactionalId());
        DefaultKafkaProducerFactory<String, MessageRequest> factory = new DefaultKafkaProducerFactory<>(configProps);
        if (properties.getTransactionalId() != null) {
            factory.setTransactionIdPrefix(properties.getTransactionalId() + "_" + InetAddress.getLocalHost().getHostName());
            //factory.setProducerPerConsumerPartition(false);
        }
        return factory;
    }
}
