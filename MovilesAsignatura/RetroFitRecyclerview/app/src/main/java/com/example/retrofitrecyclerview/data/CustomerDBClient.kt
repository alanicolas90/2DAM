package com.example.retrofitrecyclerview.data


import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate


object CustomerDBClient {

    private const val BASE_URL = "http://informatica.iesquevedo.es:2326/PrimerRESTAlan-1.0-SNAPSHOT/api/"

    val apiService: CustomersService by lazy {
        val gson = GsonBuilder()
            .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
            .create()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(CustomersService::class.java)
    }
}