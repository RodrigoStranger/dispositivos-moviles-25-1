package `Practica 1 - Introduccion a Kotlin`

/*
    Autor: Rodrigo Emerson Infanzon Acosta
    Curso: Programacion De Dispositivos Moviles
    Semestre: VI
    Fecha: 14/03/2025
    Ultima modificacion:
*/

/*
    Descripción del programa:

    Escribir un programa que realice el juego de piedra, papel o tijera.
    La computadora debe elegir de manera aleatoria la opción a elegir.
    Después debe preguntar al usuario que opción quiere.
    Imprimir si ganó, perdió o empató.

    Ejemplo: si la computadora elige "piedra" y el usuario elige "tijera", el programa debe imprimir "Perdiste".
    Si la computadora elige "papel" y el usuario también elige "papel", el programa debe imprimir "Empate".
*/

// Importacion: https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.system/exit-process.html
import kotlin.system.exitProcess

// Funcion para obtener la elección aleatoria de la computadora
fun eleccionComputador(): String {
    // Lista de opciones a elegir por el computador
    val opcionesComputador = listOf("piedra", "papel", "tijera")

    // Retornar la opcion aleatoria elegida por el computador
    return opcionesComputador.random()
}

// Función para obtener la elección del usuario, validando la entrada
fun eleccionPersona(): String {
    print("Elige entre piedra, papel o tijera: ")

    // Leer entrada y convertirla a minúsculas
    val eleccionPersona = readlnOrNull()?.lowercase() ?: ""

    // Si la elección es válida, la retornamos
    if (eleccionPersona in listOf("piedra", "papel", "tijera")) {
        return eleccionPersona
    }

    // Si la elección no es válida, finalizamos la ejecución del programa
    println("¡¡¡Opción no válida!!!")
    println("\nIntentalo denuevo...")
    exitProcess(0)
}


// Función para determinar el resultado del juego
fun determinarResultado(eleccionPersona: String, eleccionComputadora: String): String {
    return when {
        // Caso de empate: ambas elecciones son iguales
        eleccionPersona == eleccionComputadora -> "¡Empate!"

        // Casos en los que la persona gana
        (eleccionPersona == "piedra" && eleccionComputadora == "tijera") ||
                (eleccionPersona == "tijera" && eleccionComputadora == "papel") ||
                (eleccionPersona == "papel" && eleccionComputadora == "piedra") -> "¡¡¡Ganaste!!!"

        // Caso contrario, es una derrota
        else -> "Perdiste..."
    }
}


fun main() {
    // Generar la elección aleatoria de la computadora
    val computadora = eleccionComputador()

    // Obtener la elección del usuario
    val persona = eleccionPersona()

    // Mostrar las elecciones de ambos
    println("\nElegiste: $persona")
    println("La computadora eligió: $computadora")

    // Determinar el resultado del juego
    val resultado = determinarResultado(persona, computadora)

    // Mostrar el resultado
    println("\n$resultado")
}