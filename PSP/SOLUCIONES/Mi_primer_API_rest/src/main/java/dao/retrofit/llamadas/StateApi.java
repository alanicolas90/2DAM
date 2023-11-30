package dao.retrofit.llamadas;

import common.Constants;
import dao.retrofit.modelo.ResponseStateItem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface StateApi {

    @GET(Constants.COUNTRIES_CISO_STATES)
    Call<List<ResponseStateItem>> getStatesByCountry(@Path(Constants.CISO) String country);
}