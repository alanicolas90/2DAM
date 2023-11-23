package com.example.retrofitrecyclerview.data.di


import com.example.retrofitrecyclerview.data.CustomersService
import com.example.retrofitrecyclerview.data.LocalDateAdapter
import com.example.retrofitrecyclerview.data.ServiceInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideServiceInterceptor(): ServiceInterceptor = ServiceInterceptor()


    @Singleton
    @Provides
    fun provideHttpClient(serviceInterceptor: ServiceInterceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(serviceInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun gson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
            .create()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(gson: Gson): GsonConverterFactory =
         GsonConverterFactory.create(gson)


    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://informatica.iesquevedo.es:2326/PrimerRESTAlan-1.0-SNAPSHOT/api/")
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideCurrencyService(retrofit: Retrofit): CustomersService =
        retrofit.create(CustomersService::class.java)

}