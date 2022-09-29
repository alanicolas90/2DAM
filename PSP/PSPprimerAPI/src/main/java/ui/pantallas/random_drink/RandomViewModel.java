package ui.pantallas.random_drink;

import dao.DaoDrinks;
import domain.modelo.drinks.drink.DrinksResponse;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class RandomViewModel {

    private final DaoDrinks daoDrinks;

    @Inject
    public RandomViewModel(DaoDrinks daoDrinks) {
        this.daoDrinks = daoDrinks;
    }

    public Either<String, DrinksResponse> getRandomDrink()
    {
        return daoDrinks.getRandomDrink();
    }
}
