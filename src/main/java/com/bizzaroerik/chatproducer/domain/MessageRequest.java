package com.bizzaroerik.chatproducer.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MessageRequest {
    String userId;
    Volume volume;
    String message;
}
