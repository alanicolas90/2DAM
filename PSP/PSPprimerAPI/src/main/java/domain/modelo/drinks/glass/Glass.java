package domain.modelo.drinks.glass;

import lombok.Data;

@Data
public class Glass {
    private String strGlass;

    @Override
    public String toString() {
        return "\n\nGlass{" +
                "strGlass='" + strGlass + '\'' +
                '}';
    }
}
