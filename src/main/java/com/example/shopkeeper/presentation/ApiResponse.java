package com.example.shopkeeper.presentation;

import java.time.Instant;

/**
 * Standard presentation envelope for frontend/API consumers.
 */
public record ApiResponse<T>(boolean success, T data, String message, Instant timestamp) {
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, data, null, Instant.now());
    }

    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<>(true, data, "Created successfully", Instant.now());
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, null, message, Instant.now());
    }
}
