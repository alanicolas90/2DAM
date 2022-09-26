package domain.modelo.drinks.ingrediente;

import lombok.Data;

@Data
public class DrinkIngredienteSearch {
    private int idDrink;
    private String strDrink;
    private String strDrinkThumb;

    @Override
    public String toString() {
        return "\n\nDrinkIngrtedienteSearch{" +
                "\n strDrink='" + strDrink + '\'' +
                ",\n strDrinkThumb='" + strDrinkThumb + '\'' +
                ",\n idDrink=" + idDrink +
                '}';
    }
}
