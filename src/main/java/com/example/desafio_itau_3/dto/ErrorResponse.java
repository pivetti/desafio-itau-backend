package com.example.desafio_itau_3.dto;

import java.time.OffsetDateTime;
import java.util.List;

public class ErrorResponse {
    private final OffsetDateTime timestamp = OffsetDateTime.now();
    private final int status;
    private final String error;
    private final List<String> messages;
    private final String path;

    public ErrorResponse(int status, String error, List<String> messages, String path){
        this.status = status;
        this.error = error;
        this.messages = messages;
        this.path = path;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public List<String> getMessages() {
        return messages;
    }

    public String getPath() {
        return path;
    }

}
