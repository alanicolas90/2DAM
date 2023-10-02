package com.example.appalanpantalla.ui.pantallaMain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.text.set
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
        changeName()
        observarViewModel()

    }

    private fun changeName(){
        with(binding){
            txtName.text = viewModel.getPersona(0).nombre
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