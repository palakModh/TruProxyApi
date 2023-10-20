package com.demo.truproxyapi.exceptionhandler;

import com.demo.truproxyapi.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleCustomException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                        body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "There is some issue. Please try again later."));
    }
}
