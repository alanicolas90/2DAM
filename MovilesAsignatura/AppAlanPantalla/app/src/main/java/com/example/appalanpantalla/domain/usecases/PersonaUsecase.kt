package com.example.appalanpantalla.domain.usecases

import com.example.appalanpantalla.data.Repository
import com.example.appalanpantalla.domain.modelo.Persona

class PersonaUsecase {
    fun getSize(): Int? {
        return Repository.getSizePersonas()
    }

    fun getPersona(idPersona: Int): Persona? {
        return Repository.getPersona(idPersona)
    }

}
