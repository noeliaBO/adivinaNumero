import java.io.File

const val COLOR_ACIERTOS = "\u001B[42m"
const val COLOR_COINCIDENCIA = "\u001B[43m"
const val RESET = "\u001B[0m"
const val MAX_INTENTOS = 4
fun main(args: Array<String>) {
    var ganaste = false
    var intento = 0
    var intentos = mutableListOf<Int>()
    val num1 = (1..6).random()
    var num2 = (1..6).random()
    while (num1 == num2) {
        num2 = (1..6).random()
    }
    var num3 = (1..6).random()
    while (num1 == num3 || num2 == num3) {
        num3 = (1..6).random()
    }
    var num4 = (1..6).random()
    while (num1 == num4 || num2 == num4 || num3 == num4) {
        num4 = (1..6).random()
    }
    val numeros = listOf(num1, num2, num3, num4)
    var opcion = 0
    while (opcion != 3 && intentos.size != MAX_INTENTOS && !ganaste) {

        menu()
        var escoger = readln()
        try {
            opcion = escoger.toInt()
            if (opcion <= 0 || opcion > 3) {
                println("Opción incorrecta")
            }
        } catch (e: Exception) {
            println("Opcion incorrecta.No se permite letras")
        }
        //opcion = readln().toInt()
        if (opcion == 1) {

          var  exito = false
            while (!exito) {
                println("Teclea un numero de cuatro cifras sin numeros repetidos: ")
                try {
                    var nuevoIntento = readln()
                    intento = nuevoIntento.toInt()
                    intentos.add(intento)
                    ganaste = comprobarNumero(intento, numeros)
                    exito = true
                } catch (ex: Exception) {
                    println("No se permiten letras")
                }
            }

        }
        if (opcion == 2) {
            var numIntento = 0
            for (j in intentos) {
                comprobarNumero(j, numeros)
            }
        }

    }
    File("ultimoIntento.txt").writeText(intento.toString())
    if (intentos.size == MAX_INTENTOS) {
        println("Lo siento,no has adivinado el numero $num1 $num2 $num3 $num4 en dos intentos")
    } else if (ganaste) {
        println("Felicidades has acertado")
    } else {
        println("Gracias por jugar")

    }


}


fun menu() {
    println("1. jugar")
    println("2. ver trazas de último intento")
    println("3. salir")
    println("opción: ")

}

fun comprobarNumero(intento: Int, numeros: List<Int>): Boolean {
    var coincidencia = 0
    var acierto = 0

    val comprobar = intento.toString().map { it.digitToInt() }
    var count = 0
    for (i in comprobar) {
        if (i == numeros[count]) {
            acierto++

        } else if (i in numeros) {
            coincidencia++

        }
        count++
    }

    println("$intento  ${COLOR_ACIERTOS} $acierto ${COLOR_COINCIDENCIA} $coincidencia ${RESET}")
    if (acierto == 4) {
        return true
    }
    return false
}