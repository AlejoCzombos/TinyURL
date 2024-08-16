package com.example.tiny_url.infrastructure.rest.interceptor.exception;

public class AliasAlreadyExistsException extends RuntimeException{

    public final static String MESSAGE_ALIAS_ALREADY_EXISTS = "Alias already exists";

    public AliasAlreadyExistsException(String message){ super(message);}
    public AliasAlreadyExistsException(String message, Throwable cause){ super(message, cause);}

}
