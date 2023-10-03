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


    fun getSize(){
        val size = personaUsecase.getSize()
        _uiState.value = MainState(
            personasSize = size
        )
    }

    fun getPersona(idPersona: Int) {
        val persona = personaUsecase.getPersona(idPersona)
        _uiState.value = MainState(
            persona = _uiState.value.let { persona }
        )
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

