package client.dao.retrofit;


import client.common.Constants;
import client.common.config.Configuration;
import client.dao.retrofit.llamadas.AlbumApi;
import client.dao.retrofit.llamadas.ArtistApi;
import client.dao.retrofit.llamadas.LoginApi;
import client.dao.retrofit.llamadas.SongApi;
import client.dao.retrofit.network.JavaNetCookieJar;
import com.google.gson.*;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ProducesRetrofit {

    @Produces
    @Singleton
    public Gson getGson() {
        //Gson with LocalDateTime and LocalDate adapters
        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> LocalDateTime.parse(json.getAsJsonPrimitive().getAsString()))
                .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (localDateTime, type, jsonSerializationContext) -> new JsonPrimitive(localDateTime.toString()))
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) -> LocalDate.parse(json.getAsJsonPrimitive().getAsString()))
                .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (localDate, type, jsonSerializationContext) -> new JsonPrimitive(localDate.toString()))
                .create();
    }


    @Produces
    public OkHttpClient getOKHttpClient() {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

        return new OkHttpClient.Builder()
                .readTimeout(Duration.of(10, ChronoUnit.MINUTES))
                .callTimeout(Duration.of(10, ChronoUnit.MINUTES))
                .connectTimeout(Duration.of(10, ChronoUnit.MINUTES))
                .connectionPool(new okhttp3.ConnectionPool(Constants.ONE, Constants.ONE, java.util.concurrent.TimeUnit.SECONDS))
                //Needed to maintain the session
                .cookieJar(new JavaNetCookieJar(cookieManager))
                .build();
    }

    @Produces
    @Singleton
    public Retrofit retrofits(OkHttpClient clientOK, Gson gson, Configuration config) {
        return new Retrofit.Builder()
                .baseUrl(config.getBaseURL())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(clientOK)
                .build();
    }

    @Produces
    public ArtistApi getArtistApi(Retrofit retrofit) {
        return retrofit.create(ArtistApi.class);
    }

    @Produces
    public SongApi getSongApi(Retrofit retrofit) {
        return retrofit.create(SongApi.class);
    }

    @Produces
    public AlbumApi getAlbumApi(Retrofit retrofit) {
        return retrofit.create(AlbumApi.class);
    }

    @Produces
    public LoginApi getLoginApi(Retrofit retrofit) {
        return retrofit.create(LoginApi.class);
    }
}