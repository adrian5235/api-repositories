package com.adrian.api.exception;

public record Error(
        int status,
        String message
) {}
