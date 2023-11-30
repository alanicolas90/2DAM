package org.example.domain.model.exceptions;

public class NotValidFieldException extends RuntimeException {

    public NotValidFieldException(String message) {
        super(message);
    }
}