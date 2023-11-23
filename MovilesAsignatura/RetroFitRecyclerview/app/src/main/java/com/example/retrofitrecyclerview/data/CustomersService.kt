package com.example.retrofitrecyclerview.data

import com.example.retrofitrecyclerview.domain.Customer
import retrofit2.Response
import retrofit2.http.GET

interface CustomersService {

    @GET("customers")
    fun getAllCustomers(): Response<List<Customer>>

}