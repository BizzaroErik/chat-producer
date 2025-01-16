package com.bizzaroerik.chatproducer.configuration.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.jackson.JsonCloudEventData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.serializer.FailedDeserializationInfo;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.function.Function;

@Slf4j
public class DeserializationErrorHandler implements Function<FailedDeserializationInfo, CloudEvent> {
    private static final JsonNode DATA = new ObjectMapper().createObjectNode();

    @Override
    public CloudEvent apply(FailedDeserializationInfo info) {
        log.error("Failed to deserialize data from topic {}",
                info.getTopic(),
                info.getException());
        if (log.isDebugEnabled() && info.getData() != null) {
            log.debug("Could not deserialize value: {}", new String(info.getData()));
        }
        return CloudEventBuilder.v1()
                .withId("DESERIALIZATION_FAILED")
                .withType("DESERIALIZATION_FAILED")
                .withoutDataSchema()
                .withData(JsonCloudEventData.wrap(DATA))
                .withSource(URI.create("deserialization:failure"))
                .withTime(OffsetDateTime.now())
                .build();
    }
}
