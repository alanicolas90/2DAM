package com.example.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

private val tag = ":::TAG"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //variables()
        // tiposDeDatos()
        //operadores()
        //nullSafety()
        //funciones()
        //clases()
        //interfaces()
//        val rodri = Programador()
//        rodri.getProgrammerData().let {
//            Log.d(tag, "${it.name} ${it.age} ${it.language}")
//        }
        //condicionalesif()
        //condicionalesWhen()
        lsitados()
    }

    private fun lsitados() {
        val list = listOf<String>()
        val list2: List<String> = listOf()

        val arrayList = arrayListOf<String>()
        val arrayList2: ArrayList<String> = arrayListOf()

        val myList = listOf("Rodrigo", "Jose", "Juan")
        val myArrayList = arrayListOf("Rodrigo", "Jose", "Juan")

        
    }


    private fun condicionalesWhen() {
        val language = "Kotlin"
        when(language){
            "Kotlin" , "Scala"-> {
                Log.d(tag, "Kotlin")
            }
            "Java" -> Log.d(tag, "Java")
            "Python" -> Log.d(tag, "Python")
            else -> Log.d(tag, "No se que lenguaje es")
        }

        val num = 94
        when(num){
            in 0..10-> {
                Log.d(tag, "Kotlin")
            }
            40 -> Log.d(tag, "Java")
            in 80..120 -> Log.d(tag, "Python")
            else -> Log.d(tag, "No se que lenguaje es")
        }
    }


    private fun condicionalesif() {
        val num1 = 10
        val num2 = 5
        val booleano = true

        if (num1 > num2) {
            Log.d(tag, "num1 es mayor que num2")
        }  else {
            Log.d(tag, "num1 es menor que num2")
        }
        if(booleano) Log.d(tag, "Es true") else Log.d(tag, "Es false")
        val resultado:Int = if (!booleano){
            17
        }else{
            18
        }
        Log.d(tag, "$resultado")
    }


    private fun interfaces() {
        val rodrigo = Persona(PersonaData("Rodrigo", 26))

        rodrigo.returnAge(1995)
        rodrigo.presentacion()
    }

    interface PersonaInterface {
        fun returnAge(birthYear: Int): Int
    }


    class Persona(private val data: PersonaData) : PersonaInterface {
        fun presentacion() {
            data.name?.let {//si es null no se ejecuta
                Log.d(tag, "Hola soy ${data.name} y mi edad es ${data.age}")
            } ?: run {//si es null se ejecuta
                Log.d(tag, "Hola soy juancho y mi edad es ${data.age}")
            }
        }

        override fun returnAge(birthYear: Int): Int = Log.d(tag, "Mi edad es ${2021 - birthYear}")

    }


    data class PersonaData(
        val name: String?,
        val age: Int
    )

    private fun clases() {
        val rodrigoData = PersonaData(null, 26)
        val rodrigo = Persona(rodrigoData)
        rodrigo.presentacion()
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

    private fun funcionSimple() {
        Log.d(tag, "Jose")
        Log.d(tag, 30.toString())
    }

    private fun printNameAge(name: String, age: Int) {
        //Log.d(tag, "Mi nombre es $name y mi edad es $age")
        Log.d(tag, name)
        Log.d(tag, age.toString())
    }

    //para trozos de codigo cortos se puede hacer asi (es como un return) y se sobre entiende el :Int (se puede borrar)
    private fun funcionRetorno(birthYear: Int): Int = 2021 - birthYear

    private fun nullSafety() {
        var nullString: String? = null
        if (nullString != null) {
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