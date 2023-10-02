package com.example.appalanpantalla.ui.pantallaMain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.appalanpantalla.databinding.ActivityMainBinding
import com.example.appalanpantalla.domain.usecases.PersonaUsecase
import com.example.appalanpantalla.utils.StringProvider

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            StringProvider.instance(this),
            PersonaUsecase()
        )
    }
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        eventos()

        observarViewModel()

    }

    private fun eventos() {
        with(binding){
            button.setOnClickListener {
                txtName.text = viewModel.getPersona(0).nombre
            }
        }
    }

    private fun observarViewModel() {
        viewModel.uiState.observe(this@MainActivity) { state ->
            state.message?.let { error ->
                Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                viewModel.errorMostrado()
            }
            if (state.message == null)
                binding
        }
    }
}