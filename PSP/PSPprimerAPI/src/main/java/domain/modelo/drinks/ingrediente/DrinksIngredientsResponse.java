package domain.modelo.drinks.ingrediente;

import lombok.Data;

import java.util.List;

@Data
public class DrinksIngredientsResponse {
    private List<DrinkIngredienteSearch> drinks;

    @Override
    public String toString() {
        return "DrinksIngredientsResponse{" +
                "\nlistaBebidasBuscadasIngrediente=" + drinks +
                "}";
    }
}
