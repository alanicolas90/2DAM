package dao;

import com.squareup.moshi.Moshi;
import common.config.Configuracion;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Singleton
public class RetroFit {

    private final Configuracion configuracion;

    @Inject
    private RetroFit(Configuracion config){
        this.configuracion = config;
    }

    public Retrofit getRetrofit(){
        Moshi moshi = new Moshi.Builder().build();

        OkHttpClient clientOK = new OkHttpClient.Builder()
                .connectionPool(new okhttp3.ConnectionPool(1, 1, java.util.concurrent.TimeUnit.SECONDS))
                .build();


        // Como hacer el link tirarlo de config
        return new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
//                .baseUrl(configuracion.getPathDatos())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(clientOK)
                .build();
    }
}
