package domain.modelo.drinks.ingrediente;

import lombok.Data;

@Data
public class Ingrediente {
    private String strIngredient1;

    @Override
    public String toString() {
        return "\n\nIngrediente{" +
                "\nstrIngredient1='" + strIngredient1 + '\'' +
                '}';
    }
}
