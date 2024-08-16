package com.example.tiny_url.infrastructure.rest.interceptor.exception;

public class UrlHasExpiredException extends RuntimeException {

    public final static String MESSAGE_URL_HAS_EXPIRED = "Url has expired";

    public UrlHasExpiredException(String message){ super(message);}
    public UrlHasExpiredException(String message, Throwable cause){ super(message, cause);}

}
