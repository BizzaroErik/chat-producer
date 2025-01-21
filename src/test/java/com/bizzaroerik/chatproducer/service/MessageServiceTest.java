package com.bizzaroerik.chatproducer.service;

import com.bizzaroerik.chatproducer.domain.MessageRequest;
import com.bizzaroerik.chatproducer.domain.Volume;
import com.bizzaroerik.chatproducer.kafka.producer.MessageProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {

    @Mock
    MessageProducer producer;

    @InjectMocks
    MessengerService messengerService;

    @Test
    void testCompleteOrderInvoiceRequestSuccess() {
        var userId = "aptapt";
        var request = MessageRequest.builder().userId(userId).volume(Volume.WHISPER).message("Hello Kafka!").build();
        var messageSentResponse = messengerService.sendMessage(request);
        assertThat(messageSentResponse.getUserId()).isEqualTo(userId);
    }
}
