package com.example.tiny_url.infrastructure.rest.interceptor.exception;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Value
public class MessageException {
    private String message;
    private String uri;
}
