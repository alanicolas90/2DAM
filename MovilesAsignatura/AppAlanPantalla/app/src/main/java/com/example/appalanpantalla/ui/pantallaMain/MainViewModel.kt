package com.example.appalanpantalla.ui.pantallaMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appalanpantalla.domain.usecases.PersonaUsecase
import com.example.appalanpantalla.utils.StringProvider


class MainViewModel(
    private val stringProvider: StringProvider,
    private val personaUsecase: PersonaUsecase,
) : ViewModel() {

    private var idPersona= 0
    private val _uiState = MutableLiveData<MainState>()
    val uiState: LiveData<MainState> get() = _uiState


    init{
        this.initialize()
    }

    fun initialize() {
        val persona = personaUsecase.getPersona(idPersona)
        val size = personaUsecase.getSize()
        _uiState.value = MainState(
            persona = persona,
            personasSize = size
        )
    }

    fun getNextPersona() {
        if (idPersona < _uiState.value?.personasSize!!) {
            idPersona += 1
            val persona = personaUsecase.getPersona(idPersona)
            _uiState.value = MainState(
                message = null,
                persona = persona,
                personasSize = _uiState.value?.personasSize
            )
        } else {
            _uiState.value = MainState(
                message = "No hay más personas"
            )
        }
    }

    fun getIdPersona(): Int {
        return idPersona
    }
    fun getBeforePersona() {
        if (idPersona > 0) {
            idPersona -= 1
            val persona = personaUsecase.getPersona(idPersona)
            _uiState.value = _uiState.value?.copy(
                message = null,
                persona = persona,
                personasSize = _uiState.value?.personasSize
            )

        } else {
            _uiState.value = _uiState.value?.copy(message = "No hay más personas")
        }
    }

    fun errorMostrado() {
        _uiState.value = _uiState.value?.copy(message = null)
    }


}


/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class MainViewModelFactory(
    private val stringProvider: StringProvider,
    private val addPersona: PersonaUsecase,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                stringProvider,
                addPersona,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

