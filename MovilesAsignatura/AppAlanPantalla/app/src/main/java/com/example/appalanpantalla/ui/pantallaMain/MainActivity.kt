package com.example.appalanpantalla.ui.pantallaMain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.appalanpantalla.R
import com.example.appalanpantalla.databinding.ActivityMainBinding
import com.example.appalanpantalla.domain.usecases.AddPersonaUsecase
import com.example.appalanpantalla.utils.StringProvider

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            StringProvider.instance(this),
            AddPersonaUsecase()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        observarViewModel()
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