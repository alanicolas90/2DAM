package com.example.appalanpantalla.domain.usecases

import com.example.appalanpantalla.data.Repository

class GetSizePersonasUseCase {

    operator fun invoke() = Repository.getSizePersonas()

}