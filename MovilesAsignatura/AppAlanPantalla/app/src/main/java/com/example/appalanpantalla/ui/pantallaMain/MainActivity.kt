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
        with(binding) {
            buttonNext.setOnClickListener {
                viewModel.getNextPersona()
                buttonBack.isEnabled = true
            }
            buttonBack.setOnClickListener {
                viewModel.getBeforePersona()
                buttonNext.isEnabled = true
            }
        }
    }


    private fun observarViewModel() {
        viewModel.uiState.observe(this@MainActivity) { state ->
            state.message?.let { error ->
                Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                viewModel.errorMostrado()
            }
            if (state.message == null) {
                with(binding) {
                    txtName.setText(state.persona?.nombre)
                    txtSizeList.text = "Size list: " + state.personasSize.toString()
                    txtSurname.setText(state.persona?.apellido)
                    if (viewModel.getIdPersona() + 1 == state.personasSize) {
                        buttonNext.isEnabled = false
                    } else if (viewModel.getIdPersona() == 0) {
                        buttonBack.isEnabled = false
                    }
                }
            }
        }
    }
}