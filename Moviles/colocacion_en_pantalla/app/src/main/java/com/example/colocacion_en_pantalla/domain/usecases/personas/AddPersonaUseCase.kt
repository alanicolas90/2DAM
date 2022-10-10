package com.example.colocacion_en_pantalla.domain.usecases.personas

import com.example.colocacion_en_pantalla.data.RespositoryDos
import com.example.colocacion_en_pantalla.domain.modelo.Persona

class AddPersonaUseCase {

    operator fun invoke(persona: Persona) =
        RespositoryDos.addPersona(persona)

}