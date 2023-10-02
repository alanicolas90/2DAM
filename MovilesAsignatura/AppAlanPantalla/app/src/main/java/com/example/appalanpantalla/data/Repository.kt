package com.example.appalanpantalla.data

import com.example.appalanpantalla.domain.modelo.Persona

object Repository {
    private val personas = mutableListOf<Persona>()
    private val mapPersonas = mutableMapOf<String, Persona>()

    init {
        personas.add(Persona("Pepito","Grillo","M",true,1800))
    }

    fun addPersona(persona: Persona) = personas.add(persona)

    fun getPersonas(): List<Persona> {
        return personas
    }
}