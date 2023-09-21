package model;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class Credentials {
    private String username;
    private String password;
}
