package ui.main;

import dao.DaoDrinks;
import domain.modelo.drinks.drink.Drink;
import domain.modelo.drinks.drink.DrinksResponse;
import domain.usecases.DrinksUseCase;
import io.vavr.control.Either;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;


public class Main {

    public static void main(String[] args) {

        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();

        DaoDrinks daoDrinks = container.select(DaoDrinks.class).get();
        DrinksUseCase drinksUseCase = container.select(DrinksUseCase.class).get();

        Either<String, DrinksResponse> drink = daoDrinks.getRandomDrink();

//        System.out.println(daoDrinks.getDrinkNameRandom());
//        System.out.println(drink);
//        System.out.println(drink.map(DrinksResponse::getDrinks).map(drinks -> drinks.get(0)).map(Drink::getStrDrink));

        DrinksResponse drinks = drinksUseCase.getRandomDrink();

        System.out.println(drinks.getDrinks().get(0).getStrDrink());
        System.out.println(drinks);
    }
}
