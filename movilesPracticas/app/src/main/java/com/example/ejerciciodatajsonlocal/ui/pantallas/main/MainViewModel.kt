package com.example.ejerciciodatajsonlocal.ui.pantallas.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ejerciciodatajsonlocal.domain.usecases.GetAllPokemonUseCase
import com.example.ejerciciodatajsonlocal.ui.pantallas.detail.DetailViewModel

class MainViewModel(
    getAllPokemonUseCase: GetAllPokemonUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData<MainState>()
    val uiState: LiveData<MainState> get() = _uiState
    init {
        _uiState.value = MainState(
            pokemons = getAllPokemonUseCase()
        )
    }

}



//TODO CAMBIARLOOOOOOOOOOOOO

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class DetailViewModelFactory(
    private val getAllPokemonUseCase: GetAllPokemonUseCase,
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return MainViewModel(
                getAllPokemonUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}