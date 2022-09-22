package com.example.primeraappmovil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var bttnIniciarSesion : Button
    lateinit var txtPlain: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtPlain = this.findViewById(R.id.textazo)
        bttnIniciarSesion = this.findViewById(R.id.button)

        bttnIniciarSesion.setOnClickListener {
            txtPlain.text = "majali"
        }
    }

}