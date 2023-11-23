package com.example.retrofitrecyclerview.data.di

import com.example.retrofitrecyclerview.data.CustomersService
import com.example.retrofitrecyclerview.data.model.BaseApiResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val customersService: CustomersService) :
    BaseApiResponse() {

    suspend fun getCustomers() = safeApiCall(apiCall = { customersService.getAllCustomers()})
}

