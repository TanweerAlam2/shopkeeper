package com.example.shopkeeper.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class PresentationExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Void> handle(Exception exception) {
        return ApiResponse.error(exception.getMessage() == null
                ? "Request could not be processed" : exception.getMessage());
    }
}
