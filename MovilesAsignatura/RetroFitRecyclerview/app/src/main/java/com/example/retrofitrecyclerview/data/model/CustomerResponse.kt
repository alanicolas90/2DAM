package com.example.retrofitrecyclerview.data.model

import com.example.retrofitrecyclerview.domain.Customer
import java.time.LocalDate

data class CustomerResponse(
    val id: Int,
    val name: String,
    val birthDate: LocalDate,
)

fun CustomerResponse.toCustomer() : Customer = Customer(id,name,birthDate)
