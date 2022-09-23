package domain.retrofit;

import domain.modelo.drinks.DrinksResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DrinksApi {

    @GET("search.php")
    Call<DrinksResponse> getDrinkByName(@Query("s") String drinkName);
}
