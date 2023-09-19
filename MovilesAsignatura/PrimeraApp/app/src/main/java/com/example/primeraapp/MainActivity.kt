package com.example.primeraapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private lateinit var tvText: TextView
    private lateinit var btText: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvText = this.findViewById(R.id.editText)
        btText = this.findViewById(R.id.button)

        btText.setOnClickListener {
            Toast.makeText(this, tvText.text, Toast.LENGTH_LONG).show()
        }
    }
}