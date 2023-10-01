package com.example.primeraapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.primeraapp2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        eventos()
    }

    private fun eventos() {
        with(binding) {
            button.setOnClickListener {
                if (editText.text.isEmpty() || editText.text.isBlank())
                    toastCajaVacia()
                else
                    toastContentBox(editText)
            }
        }
    }

    private fun toastContentBox(editText: EditText){
        Toast.makeText(
            this@MainActivity,
            getString(R.string.txtEnLaCajaPone, editText.text), Toast.LENGTH_LONG
        ).show()
    }
    private fun toastCajaVacia() {
        Toast.makeText(this@MainActivity, getString(R.string.txtCajaVacia), Toast.LENGTH_LONG)
            .show()
    }
}
