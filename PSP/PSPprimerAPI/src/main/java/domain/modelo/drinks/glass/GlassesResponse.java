package domain.modelo.drinks.glass;

import domain.modelo.drinks.glass.Glass;
import lombok.Data;

import java.util.List;

@Data
public class GlassesResponse {
    private List<Glass> drinks;

    @Override
    public String toString() {
        return "GlassesResponse{" +
                "\ndrinks=" + drinks +
                '}';
    }
}
