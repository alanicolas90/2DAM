package domain.modelo.drinks.ingrediente;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class IngredienteName {
    private String strIngredient1;

    @Override
    public String toString() {
        return "\n\nIngrediente{" +
                "\nstrIngredient1='" + strIngredient1 + '\'' +
                '}';
    }
}
