package domain.modelo.drinks.ingrediente;

import lombok.Data;

@Data
public class Ingredient {
    private int idIngredient;
    private String strIngredient;
    private String strDescription;
    private String strType;
    private String strAlcohol;
    private String strABV;

    @Override
    public String toString() {
        return "\n\nIngredient{" +
                "\nidIngredient=" + idIngredient +
                ",\n strIngredient='" + strIngredient + '\'' +
                ", \nstrDescription='" + strDescription + '\'' +
                ", \nstrType='" + strType + '\'' +
                ", \nstrAlcohol='" + strAlcohol + '\'' +
                ", \nstrABV='" + strABV + '\'' +
                '}';
    }
}
