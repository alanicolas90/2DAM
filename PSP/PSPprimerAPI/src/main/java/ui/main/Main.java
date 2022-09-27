package ui.main;

import dao.DaoDrinks;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;


public class Main {

    public static void main(String[] args) {

        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();

        DaoDrinks daoDrinks = container.select(DaoDrinks.class).get();


        //daoDrinks.

        System.out.println(daoDrinks.getDrinkByIdIngredient(552));



//        Moshi moshi = new Moshi.Builder().build();
//
//        OkHttpClient clientOK = new OkHttpClient.Builder()
//                .connectionPool(new okhttp3.ConnectionPool(1, 1, java.util.concurrent.TimeUnit.SECONDS))
//                //.protocols(java.util.Arrays.asList(Protocol.HTTP_2,Protocol.H2_PRIOR_KNOWLEDGE))
//                .build();

//        System.out.println(clientOK
//                .newCall(
//                        new Request.Builder()
//                                .url("https://v2.jokeapi.dev/joke/Dark?lang=en").build())
//                .execute().body().string());

//        System.out.println(clientOK
//                .newCall(
//                        new Request.Builder()
//                                .url("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita").build())
//                .execute().body().string());

//        Retrofit retro = new Retrofit.Builder()
//                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
//                .addConverterFactory(MoshiConverterFactory.create(moshi))
//                //.addConverterFactory(GsonConverterFactory.create(gson))
//                .client(clientOK)
//                .build();
//
//        DrinksApi drinks= retro.create(DrinksApi.class);


//        Response<DrinksResponse> response = drinks.getDrinkByName("margarita").execute();
//        Response<DrinksResponse> randomResponse = drinks.getRandomDrink().execute();
//        Response<DrinksResponse> categoryResponse = drinks.getIngredients().execute();
//
//        System.out.println(categoryResponse.body());

        //System.out.println(response.body().toString());
        //System.out.println(randomResponse.body());
        //Response<ResponseJoke> respuesta = jokes.getDarkJoke("en").execute();
//        System.out.println(jokes.getDarkJoke("en").request().url());
        //System.out.println(respuesta.body());

    }
}
