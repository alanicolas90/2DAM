package dao.retrofit.llamadas;

import common.Constants;
import dao.retrofit.modelo.ResponseCityItem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface CityApi {

    @GET(Constants.COUNTRIES_CISO_CITIES)
    Call<List<ResponseCityItem>> getCitiesByCountry(@Path(Constants.CISO) String country);
}