package com.example.ejerciciodatajsonlocal.ui.pantallas.reciclerView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejerciciodatajsonlocal.R
import com.example.ejerciciodatajsonlocal.data.Repository
import com.example.ejerciciodatajsonlocal.databinding.ReciclerViewBinding
import com.example.ejerciciodatajsonlocal.domain.model.Pokemon
import com.example.ejerciciodatajsonlocal.ui.pantallas.PokemonAdapter
import com.google.android.material.snackbar.Snackbar

class ReciclerViewActivity : AppCompatActivity() {

    private lateinit var binding: ReciclerViewBinding

    private fun click(nombre:String){
        Snackbar.make(findViewById<RecyclerView>(R.id.rvPokemon)
            , " $nombre", Snackbar.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.recicler_view)
//        binding = ReciclerViewBinding.inflate(layoutInflater).apply {
//            setContentView(root)
//        }

        val listaPokemon = listOf(Pokemon(
            id = 2,
            nombre = "Pikachu",
            imagen = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
            altura = 4,
            peso = 60,
            experienciaBase = 112,
            tipoPokemon = listOf("electric"),
        ))

        val rvPokemon = this.findViewById<RecyclerView>(R.id.rvPokemon)

        Snackbar.make(rvPokemon, " ${listaPokemon[0].nombre} ", Snackbar.LENGTH_SHORT).show()
        var adapter = PokemonAdapter(listaPokemon, ::click)

        listaPokemon.let {
            rvPokemon.adapter = adapter
            rvPokemon.layoutManager = GridLayoutManager(this@ReciclerViewActivity,1)
        }




    }
}