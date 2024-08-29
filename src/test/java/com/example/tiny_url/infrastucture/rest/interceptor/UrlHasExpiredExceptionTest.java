package com.example.tiny_url.infrastucture.rest.interceptor;

import com.example.tiny_url.infrastructure.rest.interceptor.exception.UrlHasExpiredException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UrlHasExpiredExceptionTest {

    @Test
    public void testUrlHasExpiredException() {
        UrlHasExpiredException urlHasExpiredException = new UrlHasExpiredException("Url has expired");
        assertEquals("Url has expired", urlHasExpiredException.getMessage());
    }

    @Test
    public void testUrlHasExpiredExceptionWithCause() {
        Throwable cause = new Throwable();
        UrlHasExpiredException urlHasExpiredException = new UrlHasExpiredException("Url has expired", cause);
        assertEquals("Url has expired", urlHasExpiredException.getMessage());
        assertEquals(cause, urlHasExpiredException.getCause());
    }

}
