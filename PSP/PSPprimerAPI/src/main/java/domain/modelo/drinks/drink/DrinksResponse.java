package domain.modelo.drinks.drink;

import java.util.List;

import domain.modelo.drinks.drink.Drink;
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