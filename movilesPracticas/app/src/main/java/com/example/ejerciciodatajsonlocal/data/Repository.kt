package com.example.ejerciciodatajsonlocal.data

import com.example.ejerciciodatajsonlocal.domain.model.Estadistica
import com.example.ejerciciodatajsonlocal.domain.model.Pokemon

object Repository {

    private val pokemons = mutableListOf<Pokemon>()

    init {
        pokemons.add(
            Pokemon(
                1,
                "Bulbasur",
                64,
                7,
                69,
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                listOf("grass", "poison"),
            )
        )
    }


    fun getPokemon(idPokemon: Int) = pokemons[idPokemon]
    fun getSizePokemon(): Int {
        return pokemons.size
    }

    fun getLista(): List<Pokemon> {
        return pokemons
    }
}