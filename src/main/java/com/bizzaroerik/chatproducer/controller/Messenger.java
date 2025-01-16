package com.bizzaroerik.chatproducer.controller;

import com.bizzaroerik.chatproducer.service.MessengerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping()
@RequiredArgsConstructor
@Slf4j
public class Messenger {

    @NonNull
    private final MessengerService messengerService;

    @PostMapping(value = "/user/message", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Send messages to topic")
    public ResponseEntity<String> sendMessage(
            @RequestHeader String userId,
            @RequestHeader String volume,
            @RequestBody String message) {

        MDC.put("VOLUME", volume);
        MDC.put("USER_ID", userId);
        MDC.put("MESSAGE", message);

        log.info("\n UserMessageRequest:: \n User:: {}, Volume:: {} , Message:: {}",
                userId, volume, message);

        var sentMessage = messengerService.sendMessage(userId, volume, message);

        MDC.remove("VOLUME");
        MDC.remove("USER_ID");
        MDC.remove("MESSAGE");

        return ResponseEntity.ok(sentMessage);
    }
}
