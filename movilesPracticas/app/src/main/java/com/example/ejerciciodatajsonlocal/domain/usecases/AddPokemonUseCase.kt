package com.example.ejerciciodatajsonlocal.domain.usecases

import com.example.ejerciciodatajsonlocal.data.Repository
import com.example.ejerciciodatajsonlocal.domain.model.Pokemon

class AddPokemonUseCase {

    operator fun invoke(pokemon: Pokemon) = Repository.addPokemon(pokemon)
}