package com.example.ejerciciodatajsonlocal.ui.pantallas.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ejerciciodatajsonlocal.domain.model.Pokemon
import com.example.ejerciciodatajsonlocal.domain.usecases.AddPokemonUseCase
import com.example.ejerciciodatajsonlocal.domain.usecases.GetAllPokemonUseCase
import com.example.ejerciciodatajsonlocal.domain.usecases.GetPokemonUseCase
import com.example.ejerciciodatajsonlocal.domain.usecases.GetSizePokemonUseCase
import com.example.ejerciciodatajsonlocal.domain.usecases.GetNextIdPokemonUseCase
import com.example.ejerciciodatajsonlocal.ui.pantallas.main.MainState


class DetailViewModel(
    private val getPokemonUsecase: GetPokemonUseCase,
    private val getSizePokemonUseCase: GetSizePokemonUseCase,
    private val addPokemonUseCase: AddPokemonUseCase,
    private val getNextIdPokemonUseCase: GetNextIdPokemonUseCase,
    private val getAllPokemonUseCase: GetAllPokemonUseCase,
) : ViewModel() {

    private var idPokemon = 0
    private val _uiState = MutableLiveData<DetailState>()

    val uiState: LiveData<DetailState> get() = _uiState

    init {
        _uiState.value = DetailState()
    }


    fun errorShownAlready() {
        _uiState.value = _uiState.value?.copy(message = null)
    }

    fun addPokemon(
        name: String,
        experienciaBase: Int,
        altura: Int,
        peso: Int,
        imagen: String,
        tipoPokemon: List<String>
    ) {
        //TODO MIRAR TAMBIEN SIZE DE LA LISTA Y EL RECILERVIEW
        val pokemon = Pokemon(
            id = getNextIdPokemonUseCase(),
            nombre = name,
            experienciaBase = experienciaBase,
            altura = altura,
            peso = peso,
            imagen = imagen,
            tipoPokemon = tipoPokemon,
        )

        if(addPokemonUseCase(pokemon)){
            _uiState.value = _uiState.value?.copy(
                message = "Pokemon añadido",
            )
        }
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
class DetailViewModelFactory(
    private val getPokemonUsecase: GetPokemonUseCase,
    private val getSizePokemonUseCase: GetSizePokemonUseCase,
    private val addPokemonUseCase: AddPokemonUseCase,
    private val getNextIdPokemonUseCase: GetNextIdPokemonUseCase,
    private val getAllPokemonUseCase: GetAllPokemonUseCase,
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return DetailViewModel(
                getPokemonUsecase,
                getSizePokemonUseCase,
                addPokemonUseCase,
                getNextIdPokemonUseCase,
                getAllPokemonUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


