package domain.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class State {

    private int id;
    private String name;
    private String iso2;
}