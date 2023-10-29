package com.example.ejerciciodatajsonlocal.ui.pantallas.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ejerciciodatajsonlocal.domain.model.Pokemon
import com.example.ejerciciodatajsonlocal.domain.usecases.GetPokemonUseCase
import com.example.ejerciciodatajsonlocal.domain.usecases.GetSizePokemonUseCase


class MainViewModel(
    getPokemonUsecase: GetPokemonUseCase,
    getSizePokemonUseCase: GetSizePokemonUseCase
) : ViewModel() {

    private var idPokemon = 0
    private val _uiState = MutableLiveData<MainState>()

    //funcion de get del main state para modificar cosas de las pantallas
    val uiState: LiveData<MainState> get() = _uiState

    init {
        if (getSizePokemonUseCase() == 0) {
            _uiState.value = MainState(
                message = "No hay mas pokemons"
            )
        }else{
            val pokemon = getPokemonUsecase(0)
            _uiState.value = MainState(
                pokemon = pokemon,
            )
        }
    }


    fun errorShownAlready() {
        _uiState.value = _uiState.value?.copy(message = null)
    }

    fun addPokemon(pokemon: Pokemon) {
        //TODO USECASE Y PROBAR EL ADD / MIRAR TAMBIEN SIZE DE LA LISTA Y EL RECILERVIEW
        //addPokemonUseCase(pokemon)
        _uiState.value = _uiState.value?.copy(
            pokemon = pokemon,
        )
    }

    fun deletePokemon() {
       // TODO("Not yet implemented")
        _uiState.value = _uiState.value?.copy(
            message = "Pokemon eliminado",
        )
    }

    fun updatePokemon() {
        //TODO("Not yet implemented")
        _uiState.value = _uiState.value?.copy(
            message = "Pokemon actualizado",
        )
    }


}




/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class MainViewModelFactory(
    private val getPokemonUsecase: GetPokemonUseCase,
    private val getSizePokemonUseCase: GetSizePokemonUseCase,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return MainViewModel(
                getPokemonUsecase,
                getSizePokemonUseCase,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


