package domain.usecases;

import dao.DaoDrinks;
import domain.modelo.drinks.drink.DrinksResponse;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class DrinksUseCase {

    private final DaoDrinks daoDrinks;

    @Inject
    public DrinksUseCase(DaoDrinks daoDrinks) {
        this.daoDrinks = daoDrinks;
    }


    public DrinksResponse getRandomDrink()
    {
        Either<String, DrinksResponse> drink = daoDrinks.getRandomDrink();
        if(drink.isRight()){
            return daoDrinks.getRandomDrink().get();
        }else{
            return null;
        }
    }
}
