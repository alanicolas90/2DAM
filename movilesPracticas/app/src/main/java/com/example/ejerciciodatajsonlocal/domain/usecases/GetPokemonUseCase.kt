package com.example.ejerciciodatajsonlocal.domain.usecases

import com.example.ejerciciodatajsonlocal.data.Repository

class GetPokemonUseCase {
    operator fun invoke (idPokemon: Int) = Repository.getPokemon(idPokemon)
}