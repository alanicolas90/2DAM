package com.example.appalanpantalla.data

import com.example.appalanpantalla.domain.modelo.Persona

object Repository {
    private val personas = mutableListOf<Persona>()

    init {
        personas.add(Persona("Pepito", "Grillo", 0, true, 25000F))
        personas.add(Persona("Jaimito", "Grillo", 0, true, 1800F))
        personas.add(Persona("Grillo", "Jimenez", 1, false, 1800F))
        personas.add(Persona("Alan", "Mikolajczyk", 0, true, 1800F))
    }

    fun getSizePersonas() = personas.size


    fun addPersona(persona: Persona) = personas.add(persona)

    fun deletePersona(idPersona: Int) = personas.remove(personas[idPersona])


    fun getPersona(idPersona: Int) = personas[idPersona]
    fun updatePersona(nuevaPersona: Persona, idPersona: Int) {
        personas[idPersona] = nuevaPersona
    }

}


