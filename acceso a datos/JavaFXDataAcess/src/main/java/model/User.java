package model;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class User {
    private String username;
    private String password;
}
