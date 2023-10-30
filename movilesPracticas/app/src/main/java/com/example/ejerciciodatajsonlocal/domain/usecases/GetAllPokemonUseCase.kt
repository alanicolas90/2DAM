package com.example.ejerciciodatajsonlocal.domain.usecases

import com.example.ejerciciodatajsonlocal.data.Repository

class GetAllPokemonUseCase {
    operator fun invoke() = Repository.getListAllPokemons()
}
