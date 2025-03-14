package `Practica 1 - Introduccion a Kotlin`

/*
    Autor: Rodrigo Emerson Infanzon Acosta
    Curso: Programacion De Dispositivos Moviles
    Semestre: VI
    Fecha: 14/03/2025
    Ultima modificacion: 14/03/2025 10.00 am
*/

// Función para mostrar el menú
fun mostrarMenu() {
    println("\n==== Menú ====")
    println("1. Suma")
    println("2. Resta")
    println("3. Multiplicación")
    println("4. División")
    println("5. Salir")
    print("Seleccione una opción: ")
}


// Funciones de operaciones matemáticas
fun suma(a: Int, b: Int) = a + b
fun resta(a: Int, b: Int) = a - b
fun multiplicacion(a: Int, b: Int) = a * b
fun division(a: Int, b: Int): String = if (b != 0) (a / b).toString() else "No se puede dividir entre 0"

fun calculadora(opcion: Int, numero1: Int, numero2: Int): String {
    return when (opcion) {
        1 -> "Resultado: ${suma(numero1, numero2)}"
        2 -> "Resultado: ${resta(numero1, numero2)}"
        3 -> "Resultado: ${multiplicacion(numero1, numero2)}"
        4 -> "Resultado: ${division(numero1, numero2)}"
        5 -> "Saliendo de la calculadora..."
        else -> "Error: opción no reconocida"
    }
}