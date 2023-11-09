package dao.model;


import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Getter
public class ErrorC {
    String message;
    LocalDateTime date;
    int errorNumber;

    public ErrorC(String message) {
        this.message = message;
        this.date = LocalDateTime.now();
    }

    public ErrorC(String message, int errorNumber) {
        this.message = message;
        this.date = LocalDateTime.now();
        this.errorNumber = errorNumber;
    }
}
