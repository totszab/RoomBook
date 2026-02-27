package com.example.roombook.exception;

public record ApiError(
        String code,
        String message
) {}