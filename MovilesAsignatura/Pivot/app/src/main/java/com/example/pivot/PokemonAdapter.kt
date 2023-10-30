package com.example.pivot

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ejerciciodatajsonlocal.R
import com.example.ejerciciodatajsonlocal.databinding.ItemPokemonBinding
import com.example.ejerciciodatajsonlocal.domain.model.Pokemon
import com.example.ejerciciodatajsonlocal.ui.pantallas.main.MainActivity
import com.example.ejerciciodatajsonlocal.ui.pantallas.reciclerView.ReciclerViewActivity

class PokemonAdapter(
    private var pokemon: List<Pokemon>,
    private val onClickBoton: (String) -> Unit,
) : RecyclerView.Adapter<PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonViewHolder(layoutInflater.inflate(R.layout.item_pokemon, parent, false))
    }


    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.render(pokemon[position], onClickBoton)
    }

    override fun getItemCount(): Int = pokemon.size


}

class PokemonViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemPokemonBinding.bind(view)

    fun render(
        pokemon: Pokemon,
        onClickBoton: (String) -> Unit,
    ) {

        with(binding) {
            txtNombrePokemon.text = pokemon.nombre
            view.setOnClickListener {
                onClickBoton(pokemon.nombre)
            }
            buttonPokemon.setOnClickListener {
                //close this layout and open the main activity
                val intent =  Intent(this@PokemonViewHolder, MainActivity::class.java)

            }
        }
    }
}