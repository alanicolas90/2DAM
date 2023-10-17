package com.example.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    private val tag = ":::TAG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //variables()
        // tiposDeDatos()
        //operadores()
        //nullSafety()
        //funciones()
    }




    

    private fun funciones() {
        val nombre = "Jose"
        val edad = 30
        val year = 1991
        funcionSimple()
        printNameAge("Juan", 20)
        printNameAge(nombre, edad)
        val calculaEdad = funcionRetorno(year)
        Log.d(tag, "$calculaEdad")
    }

    private fun funcionSimple(){
        Log.d(tag, "Jose")
        Log.d(tag, 30.toString())
    }

    private fun printNameAge(name:String, age:Int){
        //Log.d(tag, "Mi nombre es $name y mi edad es $age")
        Log.d(tag, name)
        Log.d(tag, age.toString())
    }

    //para trozos de codigo cortos se puede hacer asi (es como un return) y se sobre entiende el :Int (se puede borrar)
    private fun funcionRetorno(birthYear: Int): Int = 2021 - birthYear

    private fun nullSafety(){
        var nullString: String? = null
        if(nullString != null) {
            Log.d(tag, "$nullString")
        }

        //?.let es un operador que permite ejecutar un bloque de codigo si la variable no es null
        //?. run es un operador que permite ejecutar un bloque de codigo si la variable es null
        nullString?.let {
            Log.d(tag, "$nullString")
        } ?: run {
            Log.d(tag, "nullString es null")
        }

        //!! es un operador que permite ejecutar un bloque de codigo si la variable es null (jugandonosla nosotros de que pueda ser null)
        nullString?.let {
            Log.d(tag, nullString)
        } ?: run {
            Log.d(tag, nullString!!)
        }
    }

    private fun operadores() {
        val number = 5
        val number2 = 10

        //operadores aritmeticos
        val sum = number + number2
        val rest = number - number2
        val mult = number * number2
        val div = number / number2
        val mod = number % number2  //resto de una division

        //operadores de comparacion
        val isEquals = number == number2
        val isNotEquals = number != number2
        val isGreater = number > number2
        val isLess = number < number2
        val isGreaterOrEquals = number >= number2
        val isLessOrEquals = number <= number2

        //operadores logicos
        val and = isEquals && isNotEquals
        val or = isEquals || isNotEquals
        val not = !isEquals

        Log.d(tag, "Resultado: $isEquals")
    }

    private fun tiposDeDatos() {
        //cadena de texto
        val string = "My String"

        //valores numericos(int,short,long,byte)
        //var number = 10L para mostrar que es un long solo hay que
        var number = 10

        //Valores numericos decimales(float,double)
        var decimal: Double = 10.0  //lo da por hecho que es un double
        var decimal2 = 10.0F //da por hecho que es un float

        //Booleanos
        var boolean = true
        var boolean2 = false

        Log.d(tag, "My number is $number")
    }

    private fun variables() {
        //no se puede cambiar el valor de una variable (constante)
        val helloWorld = "Hello World"
        //se puede cambiar el valor de una variable
        var helloWorld2 = ""
        helloWorld2 = "Hello World 3"
        Log.d(tag, helloWorld)
        Log.d(tag, helloWorld2)
    }
}