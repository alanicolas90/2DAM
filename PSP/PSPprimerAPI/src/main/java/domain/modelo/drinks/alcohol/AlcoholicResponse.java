package domain.modelo.drinks.alcohol;

import lombok.Data;

import java.util.List;

@Data
public class AlcoholicResponse {
    List<Alcohol> drinks;

    @Override
    public String toString() {
        return "AlcoholicResponse{" +
                "drinks=" + drinks +
                '}';
    }
}
