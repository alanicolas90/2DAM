package com.example.ejerciciodatajsonlocal.ui.pantallas.reciclerView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ejerciciodatajsonlocal.databinding.ActivityMainBinding

class ReciclerViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }



    }
}