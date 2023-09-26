package com.example.primeraapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.primeraapp2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {

            button.setOnClickListener {

                toastCajaVacia()

            }
        }
    }

    private fun toastCajaVacia() {
        Toast.makeText(this@MainActivity, getString(R.string.txtCajaVacia), Toast.LENGTH_LONG)
            .show()
    }

    private fun toastEnLaCajaPone() {
        Toast.makeText(
            this@MainActivity,
            getString(R.string.txtEnLaCajaPone, binding.editText.text),
            Toast.LENGTH_LONG
        ).show()
    }
}