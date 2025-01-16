package com.bizzaroerik.chatproducer.configuration.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.util.Arrays;

/**
 * Generic value deserializer class for kafka
 *
 * @param <T> Type to deserialise the value into
 */
public class ValueDeserializer<T> extends JsonDeserializer<T> {

    public static final ObjectMapper MAPPER = new ObjectMapper();
    public static final Class<?> TARGET_TYPE_CLASS = JsonNode.class;


    @Override
    public T deserialize(String topic, Headers headers, byte[] data) {
        try {
            return MAPPER.readerFor(TARGET_TYPE_CLASS).readValue(data);
        } catch (IOException e) {
            throw new SerializationException("Can't deserialize data [" + Arrays.toString(data) +
                    "] from topic [" + topic + "]", e);
        }
    }

    @Override
    public T deserialize(String topic, @Nullable byte[] data) {
        try {
            return MAPPER.readerFor(TARGET_TYPE_CLASS).readValue(data);
        } catch (IOException e) {
            throw new SerializationException("Can't deserialize data [" + Arrays.toString(data) +
                    "] from topic [" + topic + "]", e);
        }
    }
}

