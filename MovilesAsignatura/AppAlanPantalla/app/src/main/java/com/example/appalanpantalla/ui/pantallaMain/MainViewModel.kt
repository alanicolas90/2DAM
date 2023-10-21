package com.example.appalanpantalla.ui.pantallaMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appalanpantalla.domain.modelo.Persona
import com.example.appalanpantalla.domain.usecases.AddPersonaUseCase
import com.example.appalanpantalla.domain.usecases.DeletePersonaUseCase
import com.example.appalanpantalla.domain.usecases.GetPersonaUseCase
import com.example.appalanpantalla.domain.usecases.GetSizePersonasUseCase
import com.example.appalanpantalla.domain.usecases.UpdatePersonaUseCase


class MainViewModel(
    private val getPersonaUseCase: GetPersonaUseCase,
    private val getSizePersonasUseCase: GetSizePersonasUseCase,
    private val addPersonaUseCase: AddPersonaUseCase,
    private val deletePersonaUseCase: DeletePersonaUseCase,
    private val updatePersonaUseCase: UpdatePersonaUseCase,
) : ViewModel() {

    private var idPersona = 0
    private val _uiState = MutableLiveData<MainState>()
    val uiState: LiveData<MainState> get() = _uiState

    init {
        if (getSizePersonasUseCase() == 0) {
            _uiState.value = MainState(
                message = Constantes.NO_HAY_MAS_PERSONAS
            )
        } else {
            val persona = getPersonaUseCase(idPersona)
            _uiState.value = MainState(
                persona = persona,
            )
        }
    }


    fun getSize(): Int {
        return getSizePersonasUseCase()
    }

    fun getIdPersona(): Int {
        return idPersona
    }

    fun addPersona(
        name: String, surname: String, gender: Int, works: Boolean, salary: Float
    ) {
        val persona = Persona(name, surname, gender, works, salary)
        when {
            name.isBlank() || name.isEmpty() -> {
                _uiState.value = _uiState.value?.copy(
                    message = Constantes.EL_NOMBRE_NO_PUEDE_ESTAR_VACIO,
                )
            }

            surname.isBlank() || surname.isEmpty() -> {
                _uiState.value = _uiState.value?.copy(
                    message = Constantes.EL_APELLIDO_NO_PUEDE_ESTAR_VACIO
                )
            }

            gender == -1 -> {
                _uiState.value = _uiState.value?.copy(
                    message = Constantes.EL_GENERO_NO_PUEDE_ESTAR_VACIO,
                )
            }

            this.getSize() == 0 && idPersona == 0 -> {
                addPersonaUseCase(persona)
                _uiState.value = _uiState.value?.copy(persona = persona)
            }

            else -> {
                addPersonaUseCase(persona)
                idPersona = getSize() - 1
                _uiState.value = _uiState.value?.copy(persona = persona)
            }
        }
    }

    fun deletePersona() {
        if (getSize() == 0) {
            _uiState.value = _uiState.value?.copy(
                message = Constantes.NO_HAY_MAS_PERSONAS,
                persona = Persona(
                    Constantes.EMPTY, Constantes.EMPTY, 0, false,
                    0F
                )
            )
        } else if (getSize() != 1) {
            deletePersonaUseCase(idPersona)
            if (idPersona == getSize()) {
                idPersona -= 1
            }
            val persona = getPersonaUseCase(idPersona)
            _uiState.value = _uiState.value?.copy(
                message = null,
                persona = persona,
            )
        } else {
            deletePersonaUseCase(idPersona)
            _uiState.value = _uiState.value?.copy(
                message = Constantes.NO_HAY_MAS_PERSONAS,
                persona = Persona(Constantes.EMPTY, Constantes.EMPTY, 0, false, 0F),
            )
        }
    }

    fun updatePersona(
        name: String,
        surname: String,
        gender: Int,
        works: Boolean,
        salary: Float
    ) {
        val persona = Persona(name, surname, gender, works, salary)
        when {
            name.isBlank() || name.isEmpty() -> {
                _uiState.value = _uiState.value?.copy(
                    message = Constantes.EL_NOMBRE_NO_PUEDE_ESTAR_VACIO,
                )
            }

            surname.isBlank() || surname.isEmpty() -> {
                _uiState.value = _uiState.value?.copy(
                    message = Constantes.EL_APELLIDO_NO_PUEDE_ESTAR_VACIO
                )
            }

            gender == -1 -> {
                _uiState.value = _uiState.value?.copy(
                    message = Constantes.EL_GENERO_NO_PUEDE_ESTAR_VACIO,
                )
            }

            else -> {
                updatePersonaUseCase(persona, idPersona)
                _uiState.value = _uiState.value?.copy(
                    message = null,
                    persona = persona,
                )
            }
        }
    }

    fun getNextPersona() {
        if (idPersona < this.getSize()) {
            idPersona += 1
            val persona = getPersonaUseCase(idPersona)
            _uiState.value = MainState(
                persona = persona,
                endList = false,
            )
        } else {
            _uiState.value = MainState(
                message = Constantes.NO_HAY_MAS_PERSONAS,
                endList = true,
            )
        }
    }

    fun getBeforePersona() {
        if (idPersona > 0) {
            idPersona -= 1
            val persona = getPersonaUseCase(idPersona)
            _uiState.value = _uiState.value?.copy(
                persona = persona,
                begginingList = false,
            )

        } else {
            _uiState.value = _uiState.value?.copy(
                message = Constantes.NO_HAY_MAS_PERSONAS,
                begginingList = true,
            )
        }
    }

    fun errorShownAlready() {
        _uiState.value = _uiState.value?.copy(message = null)
    }

}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class MainViewModelFactory(
    private val getPersonaUseCase: GetPersonaUseCase,
    private val getSizePersonasUseCase: GetSizePersonasUseCase,
    private val addPersonaUseCase: AddPersonaUseCase,
    private val deletePersonaUseCase: DeletePersonaUseCase,
    private val updatePersonaUseCase: UpdatePersonaUseCase,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return MainViewModel(
                getPersonaUseCase,
                getSizePersonasUseCase,
                addPersonaUseCase,
                deletePersonaUseCase,
                updatePersonaUseCase,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

