package domain.modelo.drinks;

import java.util.List;
import lombok.Data;

@Data
public class DrinksResponse {
	private List<Drink> drinks;
}