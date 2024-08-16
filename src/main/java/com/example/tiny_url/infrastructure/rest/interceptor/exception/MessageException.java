package com.example.tiny_url.infrastructure.rest.interceptor.exception;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class MessageException {
    String message;
    String uri;
}
