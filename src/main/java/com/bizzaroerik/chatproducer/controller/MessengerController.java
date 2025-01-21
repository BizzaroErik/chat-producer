package com.bizzaroerik.chatproducer.controller;

import com.bizzaroerik.chatproducer.domain.MessageRequest;
import com.bizzaroerik.chatproducer.domain.MessageSentResponse;
import com.bizzaroerik.chatproducer.service.MessengerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping()
@RequiredArgsConstructor
@Slf4j
public class MessengerController {

    @NonNull
    private final MessengerService messengerService;

    @PostMapping(value = "/user/message", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Send messages to topic")
    public ResponseEntity<MessageSentResponse> sendMessage(
            @RequestBody MessageRequest message) {

        MDC.put("VOLUME", message.getVolume().name());
        MDC.put("USER_ID", message.getUserId());
        MDC.put("MESSAGE", message.getMessage());

        log.info("\n UserMessageRequest:: \n User:: {}, Volume:: {} , Message:: {}",
                message.getUserId(), message.getVolume().name(), message.getMessage());

        var sentMessage = messengerService.sendMessage(message);

        MDC.remove("VOLUME");
        MDC.remove("USER_ID");
        MDC.remove("MESSAGE");

        return ResponseEntity.ok(sentMessage);
    }
}
