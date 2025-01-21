package com.bizzaroerik.chatproducer.integration;

import com.bizzaroerik.chatproducer.config.kafka.KafkaProducerConfigTest;
import com.bizzaroerik.chatproducer.domain.MessageRequest;
import com.bizzaroerik.chatproducer.kafka.producer.MessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(properties = {"spring.kafka.producer.bootstrap-servers = localhost:9092"})
@ContextConfiguration(classes = KafkaProducerConfigTest.class)
@EmbeddedKafka(
        partitions = 1,
        topics = {"integration-test-producer-topic"},
        brokerProperties = {
                "listeners=PLAINTEXT://localhost:9092",
                "port=9092",
                "transaction.state.log.replication.factor=1",
                "transaction.state.log.min.isr=1"
        })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
@DirtiesContext
class KafkaProducerIntegrationTest {

    private static final String TEST_TOPIC = "testTopic";

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private MessageProducer producer;

    @BeforeAll
    public void setUp() {
        log.info("Embedded Kafka Broker :: {} ", embeddedKafkaBroker.getBrokersAsString());
        //No zookeeper in new Kafka versions
        //log.info("Embedded Zookeeper Connection :: {} ", embeddedKafkaBroker.getZookeeperConnectionString());
    }

    @Test
    void testProduceData() {
        var message = MessageRequest.builder().build();
        producer.send(message);
        assertThat(true).isTrue();
    }

}
