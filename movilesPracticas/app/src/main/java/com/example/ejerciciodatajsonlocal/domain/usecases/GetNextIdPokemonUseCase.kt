package com.example.ejerciciodatajsonlocal.domain.usecases

import com.example.ejerciciodatajsonlocal.data.Repository

class GetNextIdPokemonUseCase {
    operator fun invoke() = Repository.getNextIdPokemon()
}