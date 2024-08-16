package com.example.tiny_url.infrastructure.rest.interceptor.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(UrlNotFoundException.class)
    MessageException handleResourceNotFoundException(HttpServletRequest request, UrlNotFoundException e){
        return MessageException.builder()
                .message(e.getLocalizedMessage())
                .uri(request.getRequestURI())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AliasAlreadyExistsException.class)
    MessageException handleAliasAlreadyExistsException(HttpServletRequest request, AliasAlreadyExistsException e){
        return MessageException.builder()
                .message(e.getLocalizedMessage())
                .uri(request.getRequestURI())
                .build();
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UrlHasExpiredException.class)
    MessageException handleUrlHasExpiredException(HttpServletRequest request, UrlHasExpiredException e){
        return MessageException.builder()
                .message(e.getLocalizedMessage())
                .uri(request.getRequestURI())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    MessageException handleValidationErrors(HttpServletRequest request, MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        return MessageException.builder()
                .message(fieldErrors.stream().map(FieldError::getDefaultMessage).reduce("", (a, b) -> a + b + "\n"))
                .uri(request.getRequestURI())
                .build();
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    MessageException handleGenericException(HttpServletRequest request){
        return MessageException.builder()
                .message("Internal server error")
                .uri(request.getRequestURI())
                .build();
    }

}
