package com.example.ejerciciodatajsonlocal.domain.usecases

import com.example.ejerciciodatajsonlocal.data.Repository

class GetListaPokemons {
    operator fun invoke() = Repository.getLista()
}