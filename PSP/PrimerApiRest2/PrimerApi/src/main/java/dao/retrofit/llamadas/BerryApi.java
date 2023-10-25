package dao.retrofit.llamadas;

import domain.modelo.berry.BerriesResponse;
import domain.modelo.berry.BerryResponse;
import jakarta.enterprise.inject.Produces;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BerryApi {

    @GET("berry")
    Call<BerriesResponse> getAllBerriesIds(@Query("limit") int numberLimit);

    @GET("berry/{id}")
    Call<BerryResponse> getSpecificBerry(@Path("id") int numBerry);
}
