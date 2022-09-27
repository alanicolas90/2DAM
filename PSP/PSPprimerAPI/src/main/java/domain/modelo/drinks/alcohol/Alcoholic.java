package domain.modelo.drinks.alcohol;

import lombok.Data;

@Data
public class Alcoholic {
    private String strDrink;
    private String strDrinkThumb;
    private int idDrink;

    @Override
    public String toString() {
        return "\n\nAlcoholic{" +
                "\nstrDrink='" + strDrink + '\'' +
                ",\n strDrinkThumb='" + strDrinkThumb + '\'' +
                ",\n idDrink=" + idDrink +
                '}';
    }
}
