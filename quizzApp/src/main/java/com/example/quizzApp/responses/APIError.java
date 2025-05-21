package com.example.quizzApp.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class APIError {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private int status;
    private HttpStatus error;
    private String message;
    private String debugMessage;

    public APIError() {
        timestamp = LocalDateTime.now();
    }

    public APIError(HttpStatus error) {
        this();
        this.error = error;
        this.status = error.value();
    }

    public APIError(HttpStatus error, Throwable ex) {
        this();
        this.error = error;
        this.status = error.value();
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }
    public APIError(HttpStatus error, String message, Throwable ex) {
        this();
        this.error = error;
        this.status = error.value();
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }
}