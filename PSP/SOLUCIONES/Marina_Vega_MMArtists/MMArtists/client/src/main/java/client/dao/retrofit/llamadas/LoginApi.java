package client.dao.retrofit.llamadas;

import client.common.Constants;
import domain.common.ApiConstants;
import domain.model.User;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginApi {

    @GET(Constants.LOGIN)
    Single<Response<Void>> getLogin(@Query(ApiConstants.USERNAME) String username, @Query(ApiConstants.PASSWORD) String password);

    @POST(Constants.LOGIN)
    Single<User> register(@Body User user);
}