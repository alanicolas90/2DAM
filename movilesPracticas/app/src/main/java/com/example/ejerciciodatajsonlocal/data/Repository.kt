package com.example.ejerciciodatajsonlocal.data

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
        pokemons.add(
            Pokemon(
                id = 2,
                nombre = "Pikachu",
                imagen = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
                altura = 4,
                peso = 60,
                experienciaBase = 112,
                tipoPokemon = listOf("electric"),
            )
        )
        pokemons.add(
            Pokemon(
                id = 3,
                nombre = "Charmander",
                imagen = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png",
                altura = 6,
                peso = 85,
                experienciaBase = 62,
                tipoPokemon = listOf("fire"),
            )
        )

    }


    fun getPokemon(idPokemon: Int) = pokemons[idPokemon]
    fun getSizePokemon(): Int {
        return pokemons.size
    }

    fun getListAllPokemons(): List<Pokemon> {
        return pokemons
    }

    fun getNextIdPokemon(): Int {
        return pokemons[pokemons.lastIndex].id + 1
    }

    fun addPokemon(pokemon: Pokemon) = pokemons.add(pokemon)


}