package com.example.appalanpantalla.domain.usecases

import com.example.appalanpantalla.data.Repository

class GetPersonaUseCase {

    operator fun invoke(idPersona: Int) = Repository.getPersona(idPersona)

}


