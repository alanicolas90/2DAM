package com.example.colocacion_en_pantalla.ui.pantallaMain

import com.example.colocacion_en_pantalla.domain.modelo.Persona

data class MainState(
    val persona: Persona = Persona("null", "null", 0, "null", "null"),
    val error: String? = null
)