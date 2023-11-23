package com.example.retrofitrecyclerview.ui.main

import com.example.retrofitrecyclerview.domain.Customer

data class MainState (

    private val customers:List<Customer> = emptyList()

)