package com.example.kotlin.reto1

class Programador: ProgramadorInterface {
    override fun getProgrammerData(): ProgrammerData = ProgrammerData(getName(), getAge(), getLanguage())
    private fun getName():String = "Alan"
    private fun getAge():Int = 23
    private fun getLanguage():String = "Kotlin"
}