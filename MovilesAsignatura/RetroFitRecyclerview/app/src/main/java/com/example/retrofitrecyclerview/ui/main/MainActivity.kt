package com.example.retrofitrecyclerview.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitrecyclerview.databinding.ActivityMainBinding
import com.example.retrofitrecyclerview.domain.Customer
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var customerAdapter: CustomerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(1850)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }


        customerAdapter = CustomerAdapter(this,
            object : CustomerAdapter.CustomerActions {
                override fun onDelete(customer: Customer) =
                    viewModel.handleEvent(MainEvent.DeletePersona(customer))

                override fun onStartSelectMode(customer: Customer) {
                    viewModel.handleEvent(MainEvent.StartSelectMode)
                    viewModel.handleEvent(MainEvent.SeleccionaPersona(customer))
                }

                override fun itemHasClicked(customer: Customer) {
                    viewModel.handleEvent(MainEvent.SeleccionaPersona(customer))
                }
            })


        binding.rvCustomers.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = customerAdapter
        }


    }


}


