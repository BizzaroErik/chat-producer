package com.bizzaroerik.chatproducer.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MessageRequest {
    String userId;
    String volume;
    String message;
}
