package domain.modelo.drinks;

import java.util.List;
import lombok.Data;

@Data
public class DrinksResponse {
	private List<Drink> drinks;

	@Override
	public String toString() {
		return "DrinksResponse{ \n" +
				"drinks=\n" + drinks +
				'}';
	}
}