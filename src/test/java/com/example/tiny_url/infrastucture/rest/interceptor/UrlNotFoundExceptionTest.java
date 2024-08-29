package com.example.tiny_url.infrastucture.rest.interceptor;

import com.example.tiny_url.infrastructure.rest.interceptor.exception.UrlNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UrlNotFoundExceptionTest {

    @Test
    public void testUrlNotFoundException() {
        UrlNotFoundException urlNotFoundException = new UrlNotFoundException("Url not found");
        assertEquals("Url not found", urlNotFoundException.getMessage());
    }

    @Test
    public void testUrlNotFoundExceptionWithCause() {
        Throwable cause = new Throwable();
        UrlNotFoundException urlNotFoundException = new UrlNotFoundException("Url not found", cause);
        assertEquals("Url not found", urlNotFoundException.getMessage());
        assertEquals(cause, urlNotFoundException.getCause());
    }

}
