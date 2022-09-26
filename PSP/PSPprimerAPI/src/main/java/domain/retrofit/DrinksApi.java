package domain.retrofit;

import domain.modelo.drinks.DrinksResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DrinksApi {

    @GET("search.php")
    Call<DrinksResponse> getDrinkByName(@Query("s") String drinkName);

    @GET("search.php")
    Call<DrinksResponse> getDrinkByFirstLetter(@Query("f") String firstLetterDrinkName);

    @GET("search.php")
    Call<DrinksResponse> getDrinkByIngredient(@Query("i") String ingredient);

    @GET("random.php")
    Call<DrinksResponse> getRandomDrink();

    @GET("filter.php?a=Alcoholic")
    Call<DrinksResponse> getAlcoholicDrinks();

    @GET("filter.php?a=Non_Alcoholic")
    Call<DrinksResponse> getNonAlcoholicDrinks();

    @GET("list.php?c=list")
    Call<DrinksResponse> getCategories();

    @GET("list.php?g=list")
    Call<DrinksResponse> getGlasses();

    @GET("list.php?i=list")
    Call<DrinksResponse> getIngredients();

    @GET("list.php?a=list")
    Call<DrinksResponse> getAlcoholicFilters();
}
