package domain.common;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiError {

    private String message;
    private LocalDateTime dateTime;

    public ApiError(String message) {
        this.message = message;
        this.dateTime = LocalDateTime.now();
    }
}