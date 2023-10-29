package dao.retrofit;

import com.squareup.moshi.Moshi;
import common.config.Configuration;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Singleton
public class RetroFit {

    private final Configuration configuration;

    @Inject
    private RetroFit(Configuration config){
        this.configuration = config;
    }
    @Produces
    @Singleton
    public Retrofit getRetrofit(){
        Moshi moshi = new Moshi.Builder().build();

        OkHttpClient clientOK = new OkHttpClient.Builder()
                .connectionPool(new okhttp3.ConnectionPool(1, 1, java.util.concurrent.TimeUnit.SECONDS))
                .build();


        // Como hacer el link tirarlo de config
        return new Retrofit.Builder()
                .baseUrl(configuration.getPathDatos())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(clientOK)
                .build();
    }
}
