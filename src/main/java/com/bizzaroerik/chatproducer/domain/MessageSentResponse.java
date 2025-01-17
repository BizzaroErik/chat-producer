package com.bizzaroerik.chatproducer.domain;


import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
public class MessageSentResponse {
    String userId;
    Instant sentDateTime;
}
