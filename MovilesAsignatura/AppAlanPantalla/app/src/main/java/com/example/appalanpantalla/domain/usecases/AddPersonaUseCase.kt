package com.example.appalanpantalla.domain.usecases

import com.example.appalanpantalla.data.Repository
import com.example.appalanpantalla.domain.modelo.Persona

class AddPersonaUseCase {
    operator fun invoke(persona : Persona) = Repository.addPersona(persona)
}