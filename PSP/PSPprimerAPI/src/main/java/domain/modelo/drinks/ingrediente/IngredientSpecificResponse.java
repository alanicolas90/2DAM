package domain.modelo.drinks.ingrediente;

import lombok.Data;

import java.util.List;

@Data
public class IngredientSpecificResponse {
    private List<Ingredient> ingredients;

    @Override
    public String toString() {
        return "IngredientSpecificResponse{" +
                "\ningredients=" + ingredients +
                '}';
    }
}
