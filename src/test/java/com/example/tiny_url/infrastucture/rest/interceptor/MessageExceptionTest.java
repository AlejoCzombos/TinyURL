package com.example.tiny_url.infrastucture.rest.interceptor;

import com.example.tiny_url.infrastructure.rest.interceptor.exception.MessageException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MessageExceptionTest {

    @Test
    public void testMessageException() {
        MessageException messageException1 = MessageException.builder()
                .message("Test Message")
                .uri("/test-url")
                .build();

        MessageException messageException2 = MessageException.builder()
                .message("Test Message")
                .uri("/test-url")
                .build();

        MessageException messageException3 = MessageException.builder()
                .message("Different Message")
                .uri("/test-url")
                .build();

        assertEquals(messageException1, messageException2);
        assertNotEquals(messageException1, messageException3);
        assertEquals(messageException1.hashCode(), messageException2.hashCode());
        assertNotEquals(messageException1.hashCode(), messageException3.hashCode());
    }

}
