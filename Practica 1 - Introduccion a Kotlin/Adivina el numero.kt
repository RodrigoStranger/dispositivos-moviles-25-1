package `Practica 1 - Introduccion a Kotlin`

/*
    Autor: Rodrigo Emerson Infanzon Acosta
    Curso: Programacion De Dispositivos Moviles
    Semestre: VI
    Fecha: 14/03/2025
    Ultima modificacion: 14/03/2025 11:20 am
*/

/*
    Escribe un programa que genere un número aleatorio entre 1 y 30,
    luego pida al usuario que adivine el número.
    Proporciona pistas indicando si el número a adivinar es mayor o menor que el número ingresado por el usuario.
    Continúa solicitando intentos hasta que el usuario adivine correctamente el número.
    Tienes 5 intentos, si se acaba debe imprimir game over.
    De lo contrario mostrar un mensaje de felicitación por ganar el juego.
 */

// Importacion: https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.random/-random/
import kotlin.random.Random

// Función que genera un número aleatorio entre 1 y 30
fun numeroAleatorio(): Int {
    return Random.nextInt(1, 31) // Genera un número entre 1 - 30
}

// Función que verifica si el número ingresado por el usuario es el número secreto
fun verificarNumeros(numeroUsuario: Int, numeroRandom: Int): Boolean {
    return numeroUsuario == numeroRandom
}

// Función que da una pista al usuario si no adivinó el número
fun darPista(numeroUsuario: Int, numeroSecreto: Int) {
    if (numeroUsuario < numeroSecreto) {
        println("El número secreto es mayor!!!!")
    } else {
        println("El número secreto es menor!!!")
    }
}

fun main() {
    // Generar el número secreto aleatorio
    val numeroSecreto = numeroAleatorio()

    // Establecer el número máximo de intentos
    var intentos = 5

    println("¡Bienvenido al juego de adivinanza!")
    println("\nAdivina un número entre 1 y 30")
    println("Posees $intentos intentos")

    // Bucle que se ejecuta hasta que el usuario se quede sin intentos
    while (intentos > 0) {
        print("Ingresa tu número: ")

        // Leer el número ingresado por el usuario y convertirlo a entero
        val intentoUsuario = readlnOrNull()?.toIntOrNull()

        // Validar si la entrada es válida (debe ser un número entre 1 y 30)
        if (intentoUsuario == null || intentoUsuario !in 1..30) {
            println("Entrada no válida. Ingresa un número entre 1 y 30")
            continue
        }

        // Verificar si el número ingresado es el correcto
        if (verificarNumeros(intentoUsuario, numeroSecreto)) {
            println("\n¡Felicidades! Adivinaste el número: $numeroSecreto")
            return // Termina el programa si el usuario acierta
        } else {
            darPista(intentoUsuario, numeroSecreto) // Dar una pista si no acierta
        }

        // Reducir el número de intentos restantes
        intentos--

        // Informar cuántos intentos quedan o si el juego ha terminado
        if (intentos > 0) {
            println("\n-> Te quedan $intentos intentos\n")
        } else {
            println("\nGame Over..........")
            println("El número correcto era $numeroSecreto")
        }
    }
}