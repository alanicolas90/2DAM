package domain.retrofit;

import domain.modelo.drinks.alcohol.AlcoholicIdResponse;
import domain.modelo.drinks.alcohol.AlcoholicResponse;
import domain.modelo.drinks.category.CategoriesResponse;
import domain.modelo.drinks.ingrediente.DrinksIngredientsResponse;
import domain.modelo.drinks.drink.DrinksResponse;
import domain.modelo.drinks.glass.GlassesResponse;
import domain.modelo.drinks.ingrediente.IngredientSpecificResponse;
import domain.modelo.drinks.ingrediente.IngredienteResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface DrinksApi {

    @GET("search.php")
    Call<DrinksResponse> getDrinkByName(@Query("s") String drinkName);

    @GET("search.php")
    Call<DrinksResponse> getDrinkByFirstLetter(@Query("f") String firstLetterDrinkName);

    @GET("filter.php")
    Call<DrinksIngredientsResponse> getDrinkByIngredient(@Query("i") String ingredient);

    @GET("random.php")
    Call<DrinksResponse> getRandomDrink();

    @GET("filter.php?")
    Call<AlcoholicIdResponse> getAlcoholicOrNonAlcoholicDrinks(@Query("a") String alcoOrNotAlco);

    @GET("list.php?c=list")
    Call<CategoriesResponse> getCategories();

    @GET("list.php?g=list")
    Call<GlassesResponse> getGlasses();

    @GET("list.php?i=list")
    Call<IngredienteResponse> getIngredients();

    @GET("list.php?a=list")
    Call<AlcoholicResponse> getAlcoholicFilters();

    @GET("lookup.php")
    Call<DrinksResponse> getDrinkById(@Query("i") int idDrink);

    @GET("lookup.php")
    Call<IngredientSpecificResponse> getDrinkByIdIngredient(@Query("iid") int idDrink);
}


