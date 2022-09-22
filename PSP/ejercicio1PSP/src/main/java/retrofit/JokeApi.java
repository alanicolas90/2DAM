package retrofit;

import model.jokes.ResponseJoke;
import model.jokes.ResponseJokeSimple;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JokeApi {


    @GET("Any")
    Call<ResponseJoke> getAnyJoke(@Query("lang") String lang);

    @GET("Any")
    Call<ResponseJokeSimple> getAnyJokeSimple(@Query("lang") String lang, @Query("type") String type);

    @GET("Programming")
    Call<ResponseJoke> getProgrammingJoke(@Query("lang") String lang);



}
