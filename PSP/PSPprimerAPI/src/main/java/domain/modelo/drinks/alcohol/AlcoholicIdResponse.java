package domain.modelo.drinks.alcohol;

import lombok.Data;

import java.util.List;

@Data
public class AlcoholicIdResponse {
    private List<Alcoholic> drinks;

    @Override
    public String toString() {
        return "AlcoholicIdResponse{" +
                "drinks=" + drinks +
                '}';
    }
}
