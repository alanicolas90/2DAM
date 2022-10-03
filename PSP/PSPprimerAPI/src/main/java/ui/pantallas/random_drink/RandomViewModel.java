package ui.pantallas.random_drink;


import domain.modelo.drinks.drink.DrinksResponse;
import domain.usecases.DrinksUseCase;
import jakarta.inject.Inject;

public class RandomViewModel {

    private final DrinksUseCase drinksUseCase;

    @Inject
    public RandomViewModel(DrinksUseCase drinksUseCase) {
        this.drinksUseCase = drinksUseCase;
    }

    public DrinksResponse getRandomDrink()
    {
        return drinksUseCase.getRandomDrink();
    }
}
