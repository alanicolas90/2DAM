package com.example.appalanpantalla.ui.pantallaMain

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.appalanpantalla.databinding.ActivityMainBinding
import com.example.appalanpantalla.domain.usecases.AddPersonaUseCase
import com.example.appalanpantalla.domain.usecases.DeletePersonaUseCase
import com.example.appalanpantalla.domain.usecases.GetPersonaUseCase
import com.example.appalanpantalla.domain.usecases.GetSizePersonasUseCase
import com.example.appalanpantalla.domain.usecases.UpdatePersonaUseCase
import com.google.android.material.slider.Slider


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            GetPersonaUseCase(),
            GetSizePersonasUseCase(),
            AddPersonaUseCase(),
            DeletePersonaUseCase(),
            UpdatePersonaUseCase(),
        )
    }
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        events()
        watchViewModel()
    }

    private fun events() {
        with(binding) {
            buttonNextSetOnClick()
            buttonBackSetOnClick()
            buttonAddSetOnClick()
            buttonDeleteSetOnClick()
            buttonUpdateSetOnClick()
            slider.addOnChangeListener(Slider.OnChangeListener { slider, _, _ ->
                textDineroSlider.setText(
                    buildString {
                        append(Constantes.EUR_SYMBOL_ONE_SPACE_RIGHT)
                        append(slider.value.toInt().toString())
                    }
                )
            })
        }
    }

    private fun watchViewModel() {
        viewModel.uiState.observe(this@MainActivity) { state ->
            state.message?.let { error ->
                Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                viewModel.errorShownAlready()
            }
            if (state.message == null) {
                setValuesScreen(state)
            }
        }
    }


    private fun setValuesScreen(state: MainState) {
        with(binding) {
            txtSizeList.text =
                buildString { append(Constantes.ID_WITH_SPACE + viewModel.getIdPersona()) }
            setAllScreen(state)
            buttonNext.isEnabled = state.endList
            buttonBack.isEnabled = state.begginingList
            buttonDelete.isEnabled = state.activatedButtonDelete
        }
    }


    //funciones privadas que se usan arriba
    private fun setAllScreen(state: MainState) {
        with(binding) {
            txtName.setText(state.persona?.name)
            txtSurname.setText(state.persona?.surname)
            switchTrabaja.isChecked = state.persona?.works!!
            radioGroup.check(
                if (state.persona.gender == 0) {
                    radioButtonMale.id
                } else {
                    radioButtonFemale.id
                }
            )
            if (state.persona.salary > 10000) {
                slider.value = 10000f
            } else {
                slider.value = state.persona.salary
            }
        }
    }


    private fun buttonDeleteSetOnClick() {
        binding.buttonDelete.setOnClickListener {
            viewModel.deletePersona()
        }
    }

    private fun buttonUpdateSetOnClick() {
        with(binding) {
            buttonUpdate.setOnClickListener {
                viewModel.updatePersona(
                    txtName.text.toString(),
                    txtSurname.text.toString(),
                    radioGroup.id,
                    switchTrabaja.isChecked,
                    slider.value
                )
            }
        }
    }

    private fun buttonAddSetOnClick() {
        with(binding) {
            buttonAdd.setOnClickListener {
                viewModel.addPersona(
                    txtName.text.toString(),
                    txtSurname.text.toString(),
                    radioGroup.id,
                    switchTrabaja.isChecked,
                    slider.value
                )
            }
        }
    }

    private fun buttonBackSetOnClick() {
        binding.buttonBack.setOnClickListener {
            viewModel.getBeforePersona()
        }
    }

    private fun buttonNextSetOnClick() {
        binding.buttonNext.setOnClickListener {
            viewModel.getNextPersona()
        }
    }
}
