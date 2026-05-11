package com.quan.cms.exception;

import com.quan.cms.dto.response.ApiError;
import com.quan.cms.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFound(
            ResourceNotFoundException ex
    ) {

        ApiResponse<Object> response =
                ApiResponse.builder()
                        .success(false)
                        .message(ex.getMessage())
                        .data(null)
                        .errors(List.of())
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadRequest(
            BadRequestException ex
    ) {

        ApiResponse<Object> response =
                ApiResponse.builder()
                        .success(false)
                        .message(ex.getMessage())
                        .data(null)
                        .errors(List.of())
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.badRequest()
                .body(response);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<Object>> handleUnauthorized(
            UnauthorizedException ex
    ) {

        ApiResponse<Object> response =
                ApiResponse.builder()
                        .success(false)
                        .message(ex.getMessage())
                        .data(null)
                        .errors(List.of())
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidation(
            MethodArgumentNotValidException ex
    ) {

        List<ApiError> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error ->
                        ApiError.builder()
                                .field(error.getField())
                                .message(error.getDefaultMessage())
                                .build()
                )
                .toList();

        ApiResponse<Object> response =
                ApiResponse.builder()
                        .success(false)
                        .message("Validation failed")
                        .data(null)
                        .errors(errors)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.badRequest()
                .body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<Object>> handleAccessDenied(
            AccessDeniedException ex
    ) {

        ApiResponse<Object> response =
                ApiResponse.builder()
                        .success(false)
                        .message("Access Denied")
                        .data(null)
                        .errors(List.of())
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(response);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadCredentials(
            BadCredentialsException ex
    ) {

        ApiResponse<Object> response =
                ApiResponse.builder()
                        .success(false)
                        .message("Invalid username or password")
                        .data(null)
                        .errors(List.of())
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ApiResponse<Object>> handleDisabled(
            DisabledException ex
    ) {

        ApiResponse<Object> response =
                ApiResponse.builder()
                        .success(false)
                        .message("User account is disabled")
                        .data(null)
                        .errors(List.of())
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGlobal(
            Exception ex
    ) {

        ApiResponse<Object> response =
                ApiResponse.builder()
                        .success(false)
                        .message(ex.getMessage())
                        .data(null)
                        .errors(List.of())
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}