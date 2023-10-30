package com.example.ejerciciodatajsonlocal.ui.pantallas.detail

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import coil.load
import com.example.ejerciciodatajsonlocal.databinding.ActivityDetailBinding
import com.example.ejerciciodatajsonlocal.domain.model.Pokemon
import com.example.ejerciciodatajsonlocal.domain.usecases.AddPokemonUseCase
import com.example.ejerciciodatajsonlocal.domain.usecases.GetNextIdPokemonUseCase
import com.example.ejerciciodatajsonlocal.domain.usecases.GetPokemonUseCase
import com.example.ejerciciodatajsonlocal.domain.usecases.GetSizePokemonUseCase
import com.example.ejerciciodatajsonlocal.ui.pantallas.detail.adapter.PokemonAdapter

class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(
            GetPokemonUseCase(),
            GetSizePokemonUseCase(),
            AddPokemonUseCase(),
            GetNextIdPokemonUseCase(),
        )
    }

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val POKEMON = "DetailActivity:pokemon"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        showPokemonFromIntent()
        eventos()
    }

    private fun eventos() {
        with(binding) {
            buttonAdd.setOnClickListener {
                viewModel.addPokemon(
                    name = txtPokemonName.text.toString(),
                    experienciaBase = intExperienciaBasePokemon.text.toString().toInt(),
                    altura = intAltura.text.toString().toInt(),
                    peso = intPesoPokemon.text.toString().toInt(),
                    imagen = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                    tipoPokemon = listOf("grass", "poison")
                )
                Toast.makeText(this@DetailActivity, "Añadido", Toast.LENGTH_SHORT).show()
            }
            buttonDelete.setOnClickListener {
                Toast.makeText(this@DetailActivity, "Eliminado", Toast.LENGTH_SHORT).show()
            }
            buttonUpdate.setOnClickListener {
                Toast.makeText(this@DetailActivity, "Actualizado", Toast.LENGTH_SHORT).show()
            }
            buttonShowAll.setOnClickListener {
                finish()
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun showPokemonFromIntent() {
        val pokemon = intent.getParcelableExtra<Pokemon>(POKEMON)


        //TODO esto hay que llevarlo a VIEWMODEL (PREGUNTAR A OSCAR COMO HACERLO)
        if (pokemon != null) {
            with(binding) {
                textViewIdPokemon.text = "Pokedex Number: " + pokemon.id.toString()
                txtPokemonName.setText(pokemon?.nombre)
                intAltura.setText(pokemon.altura.toString())
                intPesoPokemon.setText(pokemon.peso.toString())
                intExperienciaBasePokemon.setText(pokemon.experienciaBase.toString())
                imageView.load(pokemon.imagen)
                listViewTipoPokemon.adapter = ArrayAdapter(
                    this@DetailActivity,
                    android.R.layout.simple_list_item_1,
                    pokemon.tipoPokemon
                )
            }
        }else{
            Toast.makeText(this, "Error al cargar el pokemon", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}

