package com.example.appalanpantalla.domain.usecases

import com.example.appalanpantalla.data.Repository

class DeletePersonaUseCase {

    operator fun invoke(idPersona: Int) = Repository.deletePersona(idPersona)
}