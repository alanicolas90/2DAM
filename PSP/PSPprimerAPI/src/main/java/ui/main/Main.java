package ui.main;

import com.squareup.moshi.Moshi;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Moshi moshi = new Moshi.Builder().build();

        OkHttpClient clientOK = new OkHttpClient.Builder()
                .connectionPool(new okhttp3.ConnectionPool(1, 1, java.util.concurrent.TimeUnit.SECONDS))
                //.protocols(java.util.Arrays.asList(Protocol.HTTP_2,Protocol.H2_PRIOR_KNOWLEDGE))
                .build();

        System.out.println(clientOK
                .newCall(
                        new Request.Builder()
                                .url("https://v2.jokeapi.dev/joke/Any?lang=es").build())
                .execute().body().string());

    }
}
