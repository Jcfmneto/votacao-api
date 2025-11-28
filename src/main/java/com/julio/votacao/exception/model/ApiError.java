package com.julio.votacao.exception.model;

import java.time.Instant;

public class ApiError {

    private final Instant timestamp;
    private final String message;
    private final int  status;

    public ApiError(String message, int status) {
        this.message = message;
        this.timestamp = Instant.now();
        this.status = status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }


}
