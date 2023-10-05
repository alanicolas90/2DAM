package com.example.appalanpantalla.domain.usecases

import com.example.appalanpantalla.data.Repository
import com.example.appalanpantalla.domain.modelo.Persona

class UpdatePersonaUseCase {

    operator fun invoke(newPersona: Persona, idPersona:Int) {
        Repository.updatePersona(newPersona, idPersona)
    }
}