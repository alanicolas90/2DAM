package com.example.appalanpantalla.data

import com.example.appalanpantalla.domain.modelo.Persona

object Repository {
    private val personas = mutableListOf<Persona>()
    private val mapPersonas = mutableMapOf<String, Persona>()

    init {
        personas.add(Persona("Pepito","Grillo","M",true,1800))
        personas.add(Persona("Jaimito","Grillo","M",true,1800))
        personas.add(Persona("Grillo","Jimenez","F",true,1800))
    }

    fun getSizePersonas():Int = personas.size


    fun addPersona(persona: Persona) = personas.add(persona)


    fun getPersona(idPersona: Int): Persona {
        return personas[idPersona]
    }
}