package com.example.ejerciciodatajsonlocal.ui.pantallas.reciclerView

import com.example.ejerciciodatajsonlocal.domain.model.Pokemon
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.InputStream
import java.lang.reflect.Type

class Repository(file: InputStream? = null) {

    companion object {
        private val lista = mutableListOf<Pokemon>()

    }

    init {

        if (lista.size == 0) {
            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
            val listOfCardsType: Type = Types.newParameterizedType(
                MutableList::class.java,
                Pokemon::class.java
            )
            val ejemplo = file?.bufferedReader()?.readText()?.let { contenidoFichero ->
                moshi.adapter<List<Pokemon>>(listOfCardsType)
                    .fromJson(contenidoFichero)
            }


            lista.addAll(ejemplo!!)


            ejemplo?.let { lista.addAll(it) }
        }
    }

    fun getLista(): List<Pokemon> {
        //return lista.map { it.toPersona() }
        return lista
    }

}
