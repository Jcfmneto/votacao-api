package com.julio.votacao.exception.model;

import java.time.Instant;

public class ApiError {

    private final Instant timestamp;


    private String message;

    public ApiError(String message) {
        this.message = message;
        this.timestamp = Instant.now();
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
}
