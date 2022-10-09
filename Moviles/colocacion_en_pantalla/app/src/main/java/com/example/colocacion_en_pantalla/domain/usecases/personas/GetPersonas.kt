package com.example.colocacion_en_pantalla.domain.usecases.personas

import com.example.colocacion_en_pantalla.data.RespositoryDos

class GetPersonas {

    operator fun invoke() = RespositoryDos.getPersonas()
}