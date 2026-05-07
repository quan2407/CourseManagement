package com.quan.cms.util;

import com.quan.cms.dto.response.ApiResponse;

import java.time.LocalDateTime;
import java.util.List;

public class ResponseUtil {

    public static <T> ApiResponse<T> success(
            String message,
            T data
    ) {

        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .errors(null)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse<T> error(
            String message
    ) {

        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .data(null)
                .errors(List.of())
                .timestamp(LocalDateTime.now())
                .build();
    }
}