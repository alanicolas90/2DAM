package com.example.retrofitrecyclerview.ui.main


import com.example.retrofitrecyclerview.domain.Customer

sealed class MainEvent {


    class DeletePersonasSeleccionadas() : MainEvent()
    class DeletePersona(val customer: Customer) : MainEvent()
    class SeleccionaPersona(val customer: Customer) : MainEvent()
    class InsertPersona(val persona: Customer) : MainEvent()
    class GetPersonaPorId(val id: Int) : MainEvent()

    class GetPersonaFiltradas(val filtro: String) : MainEvent()
    object GetPersonas : MainEvent()
    object ErrorVisto : MainEvent()

    object StartSelectMode: MainEvent()
    object ResetSelectMode: MainEvent()
}
