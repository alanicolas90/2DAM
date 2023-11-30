package common;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class ApiError {

    private final String message;
    private final LocalDateTime dateTime;

    protected ApiError(String message) {
        this.message = message;
        this.dateTime = LocalDateTime.now();
    }

    protected ApiError(String message, LocalDateTime dateTime) {
        this.message = message;
        this.dateTime = dateTime;
    }
}