package com.bluesoft.bank.accounts.dtos.outputs;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExceptionResponse {

    private String errorCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final LocalDateTime timestamp;
    private final List<String> messages;

    public ExceptionResponse() {
        this.messages = new ArrayList<>();
        this.timestamp = LocalDateTime.now();
    }

    public ExceptionResponse(String errorCode, String message) {
        this.messages = Collections.singletonList(message);
        this.errorCode=errorCode;
        this.timestamp = LocalDateTime.now();
    }

    public ExceptionResponse(String errorCode,  List<String> messages) {
        this.messages = messages;
        this.errorCode=errorCode;
        this.timestamp = LocalDateTime.now();
    }

    public List<String> getMessages() {
        return messages;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
