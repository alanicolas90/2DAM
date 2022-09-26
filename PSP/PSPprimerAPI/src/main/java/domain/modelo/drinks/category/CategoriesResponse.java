package domain.modelo.drinks.category;

import lombok.Data;

import java.util.List;

@Data
public class CategoriesResponse {
    private List<Category> drinks;

    @Override
    public String toString() {
        return "CategoriesResponse{\n" +
                "drinks=" + drinks +
                '}';
    }
}
