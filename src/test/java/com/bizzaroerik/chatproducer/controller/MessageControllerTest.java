package com.bizzaroerik.chatproducer.controller;

import com.bizzaroerik.chatproducer.domain.MessageRequest;
import com.bizzaroerik.chatproducer.domain.MessageSentResponse;
import com.bizzaroerik.chatproducer.domain.Volume;
import com.bizzaroerik.chatproducer.service.MessengerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageControllerTest {

    @Mock
    MessengerService messengerService;
    @InjectMocks
    MessengerController messengerController;

    @Test
    void testOrderCompleteSuccess() {
        var userId = "aptapt";
        var request = MessageRequest.builder().userId(userId).volume(Volume.WHISPER).message("Hello Kafka!").build();
        var response = MessageSentResponse.builder().userId(userId).build();
        when(messengerService.sendMessage(request)).thenReturn(response);
        var controllerResponse = messengerController.sendMessage(request);
        assertThat(controllerResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(controllerResponse.getBody()).getUserId()).isEqualTo("aptapt");
    }

    @Test
    void testOrderCompleteWhenServiceThrowsError() {
        var userId = "tpatpa";
        var request = MessageRequest.builder().userId(userId).volume(Volume.WHISPER).message("Hello Kafka!").build();
        when(messengerService.sendMessage(request)).thenThrow(RuntimeException.class);
        assertThatThrownBy(() -> messengerController.sendMessage(request))
                .isInstanceOf(RuntimeException.class);
    }
}
