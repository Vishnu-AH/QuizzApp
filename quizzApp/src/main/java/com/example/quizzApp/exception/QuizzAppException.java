package com.example.quizzApp.exception;

import lombok.Data;

@Data
public class QuizzAppException extends RuntimeException{

    private String message;

    public QuizzAppException(String message) {
        this.message = message;
    }
}
