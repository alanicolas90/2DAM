package com.example.ejerciciodatajsonlocal.ui.pantallas.main

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import coil.load
import coil.transform.CircleCropTransformation
import com.example.ejerciciodatajsonlocal.databinding.ActivityMainBinding
import com.example.ejerciciodatajsonlocal.domain.model.Pokemon
import com.example.ejerciciodatajsonlocal.domain.usecases.GetPokemonUseCase
import com.example.ejerciciodatajsonlocal.domain.usecases.GetSizePokemonUseCase

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            GetPokemonUseCase(),
            GetSizePokemonUseCase(),
        )
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }


        events()
        watchViewModel()
    }

    private fun events() {
        buttonAddSetOnClick()
        buttonDeleteSetOnClick()
        buttonUpdateSetOnClick()
    }

    private fun buttonUpdateSetOnClick() {
        with(binding) {
            buttonUpdate.setOnClickListener {
                viewModel.updatePokemon()
            }
        }
    }

    private fun buttonDeleteSetOnClick() {
        binding.buttonDelete.setOnClickListener {
            viewModel.deletePokemon()
        }
    }


    private fun buttonAddSetOnClick() {
        with(binding) {
            buttonAdd.setOnClickListener {
                viewModel.addPokemon(
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
            }
        }
    }

    private fun watchViewModel() {
        viewModel.uiState.observe(this@MainActivity) { state ->
            state.message?.let { error ->
                Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                viewModel.errorShownAlready()
            }
            state.pokemon?.let { pokemon ->
                with(binding) {
                    textViewIdPokemon.text = "Pokemon ID: " + pokemon.id.toString()
                    txtPokemonName.setText(pokemon.nombre)
                    intAltura.setText(pokemon.altura.toString())
                    intPesoPokemon.setText(pokemon.peso.toString())
                    intExperienciaBasePokemon.setText(pokemon.experienciaBase.toString())
                    listViewTipoPokemon.adapter = ArrayAdapter(
                        this@MainActivity,
                        android.R.layout.simple_list_item_1,
                        pokemon.tipoPokemon
                    )
                    imageView.load(Uri.parse(pokemon.imagen)) {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                        crossfade(1000)
                    }
                }
            }

        }
    }
}