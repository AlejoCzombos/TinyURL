package com.example.tiny_url.infrastructure.rest.interceptor.exception;

public class UrlNotFoundException extends RuntimeException{

        public final static String URL_NOT_FOUND = "Url not found with id or alias: ";

        public UrlNotFoundException(String message){ super(message);}
        public UrlNotFoundException(String message, Throwable cause){ super(message, cause);}
}
