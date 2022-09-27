package domain.modelo.drinks.ingrediente;

import lombok.Data;

import java.util.List;

@Data
public class IngredienteResponse {
    private List<IngredienteName> drinks;

    @Override
    public String toString() {
        return "IngredienteResponse{" +
                "\ndrinks=" + drinks +
                '}';
    }
}
