package com.example.retrofitrecyclerview.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitrecyclerview.data.NetworkResultt
import com.example.retrofitrecyclerview.data.repositories.CustomerRepository
import com.example.retrofitrecyclerview.domain.Customer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val customerRepository: CustomerRepository) :ViewModel() {

    private val customerList = mutableListOf<Customer>()

    init{
        getAllCustomers()
    }

    private fun getAllCustomers() {
        viewModelScope.launch {
            var result = customerRepository.getAllCustomers()

            when (result) {
                is NetworkResultt.Error -> TODO()
                is NetworkResultt.Loading -> TODO()
                is NetworkResultt.Success -> customerList.addAll(result.data!!)
            }

            Log.d("KUKUMAPA", "getAllCustomers: $customerList")


        }
    }

    fun handleEvent(event: MainEvent) {
        when (event) {
            MainEvent.GetPersonas -> {
                getAllCustomers()
            }
            is MainEvent.InsertPersona -> {
                getAllCustomers()
            }
            is MainEvent.GetPersonaPorId -> TODO()

            is MainEvent.DeletePersonasSeleccionadas -> TODO()
            is MainEvent.SeleccionaPersona -> TODO()
            is MainEvent.GetPersonaFiltradas -> TODO()
            is MainEvent.DeletePersona -> TODO()

            MainEvent.ResetSelectMode -> TODO()

            MainEvent.StartSelectMode -> TODO()
            MainEvent.ErrorVisto -> TODO()
        }
    }

}