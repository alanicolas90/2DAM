package dao.retrofit.llamadas;

import common.Constants;
import dao.retrofit.modelo.ResponseCountryInfo;
import dao.retrofit.modelo.ResponseCountryItem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface CountryApi {

    @GET(Constants.COUNTRIES)
    Call<List<ResponseCountryItem>> getAllCountries();
    @GET(Constants.COUNTRIES_CISO)
    Call<ResponseCountryInfo> getCountryInfo(@Path(Constants.CISO) String country);
}