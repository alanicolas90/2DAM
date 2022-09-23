package ui.main;

import com.squareup.moshi.Moshi;
import domain.modelo.drinks.DrinksResponse;
import domain.retrofit.DrinksApi;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Moshi moshi = new Moshi.Builder().build();

        OkHttpClient clientOK = new OkHttpClient.Builder()
                .connectionPool(new okhttp3.ConnectionPool(1, 1, java.util.concurrent.TimeUnit.SECONDS))
                //.protocols(java.util.Arrays.asList(Protocol.HTTP_2,Protocol.H2_PRIOR_KNOWLEDGE))
                .build();

//        System.out.println(clientOK
//                .newCall(
//                        new Request.Builder()
//                                .url("https://v2.jokeapi.dev/joke/Dark?lang=en").build())
//                .execute().body().string());

        System.out.println(clientOK
                .newCall(
                        new Request.Builder()
                                .url("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita").build())
                .execute().body().string());

        Retrofit retro = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                //.addConverterFactory(GsonConverterFactory.create(gson))
                .client(clientOK)
                .build();

        DrinksApi drinks= retro.create(DrinksApi.class);

        //JokeApi jokes =  retro.create(JokeApi.class);

        Response<DrinksResponse> response = drinks.getDrinkByName("ma").execute();

        System.out.println(response.body().toString());
        //Response<ResponseJoke> respuesta = jokes.getDarkJoke("en").execute();
//        System.out.println(jokes.getDarkJoke("en").request().url());
        //System.out.println(respuesta.body());

    }
}
