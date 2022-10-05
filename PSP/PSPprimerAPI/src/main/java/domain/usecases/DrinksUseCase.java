package domain.usecases;

import dao.DaoDrinks;
import domain.modelo.drinks.alcohol.AlcoholicIdResponse;
import domain.modelo.drinks.alcohol.AlcoholicResponse;
import domain.modelo.drinks.category.CategoriesResponse;
import domain.modelo.drinks.drink.DrinksResponse;
import domain.modelo.drinks.glass.GlassesResponse;
import domain.modelo.drinks.ingrediente.DrinksIngredientsResponse;
import domain.modelo.drinks.ingrediente.IngredientSpecificResponse;
import domain.modelo.drinks.ingrediente.IngredienteResponse;
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

    public DrinksResponse getDrinkByName(String drinkName)
    {
        Either<String, DrinksResponse> drink = daoDrinks.getDrinkByName(drinkName);
        if(drink.isRight()){
            return daoDrinks.getDrinkByName(drinkName).get();
        }else{
            return null;
        }
    }

    public DrinksResponse getDrinkByFirstLetter(String firstLetter)
    {
        Either<String, DrinksResponse> drink = daoDrinks.getDrinkByFirstLetter(firstLetter);
        if(drink.isRight()){
            return daoDrinks.getDrinkByFirstLetter(firstLetter).get();
        }else{
            return null;
        }
    }

    public DrinksIngredientsResponse getDrinkByIngredient(String ingredient)
    {
        Either<String, DrinksIngredientsResponse> drink = daoDrinks.getDrinkByIngredient(ingredient);
        if(drink.isRight()){
            return daoDrinks.getDrinkByIngredient(ingredient).get();
        }else{
            return null;
        }
    }

    public AlcoholicIdResponse getAlcoholicDrinks(){
        Either<String, AlcoholicIdResponse> drink = daoDrinks.getAlcoholicDrinks();
        if(drink.isRight()){
            return daoDrinks.getAlcoholicDrinks().get();
        }else{
            return null;
        }
    }

    public AlcoholicIdResponse getNonAlcoholicDrinks(){
        Either<String, AlcoholicIdResponse> drink = daoDrinks.getNonAlcoholicDrinks();
        if(drink.isRight()){
            return daoDrinks.getNonAlcoholicDrinks().get();
        }else{
            return null;
        }
    }

    public CategoriesResponse getCategories(){
        Either<String, CategoriesResponse> drink = daoDrinks.getCategories();
        if(drink.isRight()){
            return daoDrinks.getCategories().get();
        }else{
            return null;
        }
    }

    public DrinksResponse getDrinkById(int id){
        Either<String, DrinksResponse> drink = daoDrinks.getDrinkById(id);
        if(drink.isRight()){
            return daoDrinks.getDrinkById(id).get();
        }else{
            return null;
        }
    }

    public IngredientSpecificResponse getIngredientById(int id){
        Either<String, IngredientSpecificResponse> drink = daoDrinks.getIngredientById(id);
        if(drink.isRight()){
            return daoDrinks.getIngredientById(id).get();
        }else{
            return null;
        }
    }

    public GlassesResponse getGlasses(){
        Either<String, GlassesResponse> drink = daoDrinks.getGlasses();
        if(drink.isRight()){
            return daoDrinks.getGlasses().get();
        }else{
            return null;
        }
    }

    public IngredienteResponse getIngredients(){
        Either<String, IngredienteResponse> drink = daoDrinks.getIngredients();
        if(drink.isRight()){
            return daoDrinks.getIngredients().get();
        }else{
            return null;
        }
    }

    public AlcoholicResponse getAlcoholicFilter(){
        Either<String, AlcoholicResponse> drink = daoDrinks.getAlcoholicFilter();
        if(drink.isRight()){
            return daoDrinks.getAlcoholicFilter().get();
        }else{
            return null;
        }
    }

    public String getDrinkNameRandom(){
        Either<String, DrinksResponse> drink = daoDrinks.getRandomDrink();
        if(drink.isRight()){
            return daoDrinks.getRandomDrink().get().getDrinks().get(0).getStrDrink();
        }else{
            return null;
        }
    }

}
