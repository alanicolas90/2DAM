package com.example.appalanpantalla.domain.usecases

import com.example.appalanpantalla.data.Repository
import com.example.appalanpantalla.domain.modelo.Persona

class PersonaUsecase() {
    operator fun invoke(persona: Persona) =
        Repository.addPersona(persona)

    fun getSize(): Int =
        Repository.getSizePersonas()

    fun getPersona(idPersona: Int) =
        Repository.getPersona(idPersona)


}
