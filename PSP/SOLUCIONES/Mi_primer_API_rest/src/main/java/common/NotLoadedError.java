package common;

import java.time.LocalDateTime;

public class NotLoadedError extends ApiError {
    public NotLoadedError(String message, LocalDateTime dateTime) {
        super(message, dateTime);
    }
}