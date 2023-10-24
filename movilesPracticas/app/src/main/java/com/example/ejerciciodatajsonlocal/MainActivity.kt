package com.example.ejerciciodatajsonlocal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejerciciodatajsonlocal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

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
        TODO("Not yet implemented")
    }

    private fun watchViewModel() {
        TODO("Not yet implemented")
    }
}