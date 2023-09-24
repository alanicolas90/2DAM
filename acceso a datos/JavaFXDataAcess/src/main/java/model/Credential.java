package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class Credential {
    int idCustomer;
    private String username;
    private String password;
    boolean privilege;

}
