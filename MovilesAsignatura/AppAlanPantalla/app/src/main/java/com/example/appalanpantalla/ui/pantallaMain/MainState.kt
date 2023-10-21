package com.example.appalanpantalla.ui.pantallaMain

import com.example.appalanpantalla.domain.modelo.Persona

data class MainState(
    val persona: Persona? = null,
    val message: String? = null,
    val begginingList: Boolean = false,
    val endList: Boolean = false,
)