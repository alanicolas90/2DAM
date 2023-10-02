package com.example.appalanpantalla.ui.pantallaMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appalanpantalla.domain.modelo.Persona
import com.example.appalanpantalla.domain.usecases.PersonaUsecase
import com.example.appalanpantalla.utils.StringProvider


class MainViewModel(
    private val stringProvider: StringProvider,
    private val personaUsecase: PersonaUsecase,
) : ViewModel() {

    private val _uiState = MutableLiveData<MainState>()
    val uiState: LiveData<MainState> get() = _uiState


    fun addPersona(persona: Persona) {

        if (!personaUsecase(persona)) {
            _uiState.value = MainState(
                persona = _uiState.value.let { persona },
                message = null,
                personasSize = getSize()
            )
            _uiState.value = _uiState
                .value?.copy(message = Constantes.ERROR)
        }
    }

    fun getSize(): Int {
        return personaUsecase.getSize()
    }

    fun getPersona(idPersona: Int): Persona {
        return personaUsecase.getPersona(idPersona)
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

