package com.example.colocacion_en_pantalla.data

import com.example.colocacion_en_pantalla.domain.modelo.Persona

object RespositoryDos {
    private val personas = mutableListOf<Persona>()

    init {
        personas.add(Persona("Juanito", "Perez", 123456789, "null", "null"))
        personas.add(Persona("Jorgito", "Perez", 123456789, "null", "null"))
        personas.add(Persona("Jaimito", "Perez", 123456789, "null", "null"))
    }

    private val mapPersonas = mutableMapOf<String, Persona>()

    fun addPersona(persona: Persona) =
        personas.add(persona)


    fun getPersonas(): List<Persona> {
        return personas
    }

    fun getPersona(id: String): Persona? {
        return mapPersonas[id]
    }

}