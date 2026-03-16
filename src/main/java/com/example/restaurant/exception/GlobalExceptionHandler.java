package com.example.restaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.restaurant.dto.ApiResponse;
@RestControllerAdvice
public class GlobalExceptionHandler {
     // Gestion des NotFound
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFoundException(NotFoundException e) {
        ApiResponse<Object> response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Gestion des autres exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleError(Exception e) {
        ApiResponse<Object> response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage(),
                null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}