package model;


import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Getter
public class ErrorC{
    String message;
    LocalDateTime date;

    public ErrorC( String message) {
        this.message = message;
        this.date = LocalDateTime.now();
    }
}
