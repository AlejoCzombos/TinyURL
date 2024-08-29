package com.example.tiny_url.infrastucture.rest.interceptor;

import com.example.tiny_url.infrastructure.rest.interceptor.exception.AliasAlreadyExistsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class AliasAlreadyExistsExceptionTest {

    @Test
    public void testAliasAlreadyExistsException() {
        AliasAlreadyExistsException aliasAlreadyExistsException = new AliasAlreadyExistsException("Alias already exists");
        assertEquals("Alias already exists", aliasAlreadyExistsException.getMessage());
    }

    @Test
    public void testAliasAlreadyExistsExceptionWithCause() {
        Throwable cause = new Throwable();
        AliasAlreadyExistsException aliasAlreadyExistsException = new AliasAlreadyExistsException("Alias already exists", cause);
        assertEquals("Alias already exists", aliasAlreadyExistsException.getMessage());
        assertEquals(cause, aliasAlreadyExistsException.getCause());
    }

}
