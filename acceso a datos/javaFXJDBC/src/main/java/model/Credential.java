package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
public class Credential {
    int idCustomer;
    private String username;
    private String password;
    private boolean privilege;

    public Credential(String username, String password) {
        this.username = username;
        this.password = password;
        privilege = false;
    }
}
