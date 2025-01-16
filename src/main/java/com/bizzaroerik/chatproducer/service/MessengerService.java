package com.bizzaroerik.chatproducer.service;

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

    public String sendMessage(String userId, String volume, String message) {
        messageProducer.send(userId, message);
        return "Successfully Sent: " + Instant.now() + "\n" + message;
    }
}
