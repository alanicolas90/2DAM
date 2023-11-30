package dao.retrofit;


import com.squareup.moshi.Moshi;
import common.Constants;
import common.config.Configuration;
import dao.retrofit.llamadas.CityApi;
import dao.retrofit.llamadas.CountryApi;
import dao.retrofit.llamadas.StateApi;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ProducesRetrofit {

    @Produces
    @Singleton
    public Moshi getMoshi() {
        return new Moshi.Builder().build();
    }


    @Produces
    public OkHttpClient getOK(Configuration config) {
        return new OkHttpClient.Builder()
                .connectionPool(new okhttp3.ConnectionPool(Constants.ONE, Constants.ONE, java.util.concurrent.TimeUnit.SECONDS))
                .addInterceptor(chain -> {
                            Request original = chain.request();
                            Request requestWithAPIKeyHeader = original.newBuilder()
                                    .header(Constants.HEADER_NAME, config.getApiKey()).build();
                            return chain.proceed(requestWithAPIKeyHeader);
                        }
                )
                .build();
    }

    @Produces
    @Singleton
    public Retrofit retrofits(OkHttpClient clientOK, Moshi moshi, Configuration config) {
        return new Retrofit.Builder()
                .baseUrl(config.getBaseURL())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(clientOK)
                .build();
    }

    @Produces
    public CountryApi getCountryApi(Retrofit retrofit) {
        return retrofit.create(CountryApi.class);
    }

    @Produces
    public CityApi getCityApi(Retrofit retrofit) {
        return retrofit.create(CityApi.class);
    }

    @Produces
    public StateApi getStateApi(Retrofit retrofit) {
        return retrofit.create(StateApi.class);
    }
}