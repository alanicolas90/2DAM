package com.example.ejerciciodatajsonlocal.ui.pantallas.main

import com.example.ejerciciodatajsonlocal.domain.model.Pokemon

data class MainState(
    val message:String? = null,
    val pokemon: Pokemon? = null,
    val activatedButtonDelete:Boolean = true,
    val activatedButtonUpdate:Boolean = true,
)
