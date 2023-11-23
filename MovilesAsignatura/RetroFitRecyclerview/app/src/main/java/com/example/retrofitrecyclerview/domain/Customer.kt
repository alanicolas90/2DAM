package com.example.retrofitrecyclerview.domain

import java.time.LocalDate

data class Customer (
    val id: Int,
    val name: String,
    val birthDate:LocalDate,
)