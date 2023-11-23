package com.example.retrofitrecyclerview.domain

data class Order(
    val id: Int,
    val itemName:String,
    val quantity: Int,
    val customerId: Int,
)
