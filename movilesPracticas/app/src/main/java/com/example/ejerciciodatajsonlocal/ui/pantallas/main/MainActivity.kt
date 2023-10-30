package com.example.ejerciciodatajsonlocal.ui.pantallas.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejerciciodatajsonlocal.databinding.ActivityMainBinding
import com.example.ejerciciodatajsonlocal.domain.model.Pokemon
import com.example.ejerciciodatajsonlocal.ui.pantallas.detail.DetailActivity
import com.example.ejerciciodatajsonlocal.ui.pantallas.detail.adapter.PokemonAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        binding.rvPokemon.adapter = PokemonAdapter(
            listOf(
                Pokemon(
                    id = 1,
                    nombre = "Bulbasaur",
                    imagen = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    altura = 7,
                    peso = 69,
                    experienciaBase = 64,
                    tipoPokemon = listOf("grass", "poison"),
                ),
                Pokemon(
                    id = 2,
                    nombre = "Pikachu",
                    imagen = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
                    altura = 4,
                    peso = 60,
                    experienciaBase = 112,
                    tipoPokemon = listOf("electric"),
                ),
                Pokemon(
                    id = 3,
                    nombre = "Charmander",
                    imagen = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png",
                    altura = 6,
                    peso = 85,
                    experienciaBase = 62,
                    tipoPokemon = listOf("fire"),
                ),
            )
        ) {
            navigateTo(it)
        }
    }

    private fun navigateTo(pokemon:Pokemon) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.POKEMON, pokemon)

        startActivity(intent)

    }
}