package com.example.primeraappmovil


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

//CONSTANTES
private const val EXIT = "Exit"
private const val BIENVENIDO = "Bienvenido "
private const val PASSWORD = "1234"

class MainActivity : AppCompatActivity() {

    lateinit var bttnIniciarSesion: Button
    lateinit var txtPassword: EditText
    lateinit var txtUsuario: EditText
    lateinit var txtInvisible: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtPassword = this.findViewById(R.id.password)
        txtUsuario = this.findViewById(R.id.username)
        bttnIniciarSesion = this.findViewById(R.id.button)
        txtInvisible = this.findViewById(R.id.textoInvisible)


        bttnIniciarSesion.setOnClickListener {
            if (txtUsuario.text.toString()
                    .isNotBlank() && txtPassword.text.toString() == PASSWORD
            ) {
                Toast.makeText(this, BIENVENIDO + txtUsuario.text.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                bttnIniciarSesion.text = EXIT
                txtUsuario.visibility = EditText.INVISIBLE
                txtPassword.visibility = EditText.INVISIBLE
                txtInvisible.text = BIENVENIDO + txtUsuario.text.toString()
                bttnIniciarSesion.setOnClickListener { finish() }

            } else if (txtUsuario.text.isNotBlank() && txtPassword.text.toString() != PASSWORD) {
                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Falta el usuario", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

