package com.example.ejerciciodatajsonlocal.domain.usecases

import com.example.ejerciciodatajsonlocal.data.Repository

class GetSizePokemonUseCase {
    operator fun invoke()= Repository.getSizePokemon()

}
