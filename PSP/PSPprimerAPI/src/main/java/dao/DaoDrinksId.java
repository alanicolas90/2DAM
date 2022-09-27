package dao;

import domain.modelo.drinks.drink.Drink;
import jakarta.inject.Inject;

public class DaoDrinksId {

    private final DaoDrinks daoDrinks;

    @Inject
    public DaoDrinksId(DaoDrinks daoDrinks){
        this.daoDrinks = daoDrinks;
    }


    //getDrinksByIngredient -----> id
    //getAlcoholicDrinks -----> id
    //getNonAlcoholicDrinks -----> id

//    public Drink getDrinkByIdIngredient(String string,int id){
//
//        return daoDrinks.);
//    }

}
