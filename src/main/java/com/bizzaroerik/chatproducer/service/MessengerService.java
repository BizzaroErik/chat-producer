package com.bizzaroerik.chatproducer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class MessengerService {

    public String sendMessage(String userId, String volume, String message) {
        return "Successfully Sent: " + Instant.now();
    }
}
