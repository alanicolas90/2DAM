package domain.modelo.drinks.alcohol;

import lombok.Data;

@Data
public class Alcohol {

    private String strAlcoholic;

    @Override
    public String toString() {
        return "\n\nAlcohol{" +
                "\nstrAlcoholic='" + strAlcoholic + '\'' +
                '}';
    }
}
