package com.bizzaroerik.chatproducer.service;

import com.bizzaroerik.chatproducer.domain.MessageRequest;
import com.bizzaroerik.chatproducer.domain.MessageSentResponse;
import com.bizzaroerik.chatproducer.kafka.producer.MessageProducer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessengerService {

    @NonNull
    private final MessageProducer messageProducer;

    public MessageSentResponse sendMessage(MessageRequest message) {
        messageProducer.send(message);
        return MessageSentResponse.builder()
                .userId(message.getUserId())
                .sentDateTime(Instant.now())
                .build();
    }
}
