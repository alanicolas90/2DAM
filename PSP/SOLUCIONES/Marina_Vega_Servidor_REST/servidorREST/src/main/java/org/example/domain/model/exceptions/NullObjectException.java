package org.example.domain.model.exceptions;

public class NullObjectException extends RuntimeException {

    public NullObjectException(String message) {
        super(message);
    }
}