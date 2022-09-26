package domain.modelo.drinks.category;

import lombok.Data;

@Data
public class Category {

    private String strCategory;

    @Override
    public String toString() {
        return "\nCategory{" +
                "strCategory='" + strCategory + '\'' +
                "}";
    }
}
