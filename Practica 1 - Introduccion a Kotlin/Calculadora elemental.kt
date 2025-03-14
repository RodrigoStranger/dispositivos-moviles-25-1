package `Practica 1 - Introduccion a Kotlin`

/*
    Autor: Rodrigo Emerson Infanzon Acosta
    Curso: Programacion De Dispositivos Moviles
    Semestre: VI
    Fecha: 14/03/2025
    Ultima modificacion: 14/03/2025 10.50 am
*/

/*
    Descripcion del problema:

    Realizar una calculadora básica con las operaciones:
    - Suma, Resta, Multiplicación, División y opción para Salir.

    Funcionalidad:
    1. Mostrar un menú con las opciones.
    2. Validar la opción ingresada.
    3. Solicitar dos números y ejecutar la operación elegida.
    4. Evitar división por 0.
    5. Finalizar si se elige salir o se ingresa una opción inválida.

    Ejemplo de menú:
    ==== Menú ====
    1. Suma
    2. Resta
    3. Multiplicación
    4. División
    5. Salir
*/

// Función para mostrar el menú de opciones al usuario
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
fun suma(a: Number, b: Number) = a.toDouble() + b.toDouble() // Suma dos numeros
fun resta(a: Number, b: Number) = a.toDouble() - b.toDouble() // Resta dos numeros
fun multiplicacion(a: Number, b: Number) = a.toDouble() * b.toDouble() // Multiplica dos numeros

// Función para la división con manejo de error si el divisor es 0
fun division(a: Number, b: Number): String =
    if (b.toDouble() != 0.0) (a.toDouble() / b.toDouble()).toString()
    else "No se puede dividir entre 0"

// Función que ejecuta la operación seleccionada por el usuario
fun calculadora(opcion: Int, numero1: Number, numero2: Number): String {
    // Imprime un encabezado antes del resultado
    println("\nResultado:")
    return when (opcion) {
        1 -> "$numero1 + $numero2 = ${suma(numero1, numero2)}" // Suma
        2 -> "$numero1 - $numero2 = ${resta(numero1, numero2)}" // Resta
        3 -> "$numero1 * $numero2 = ${multiplicacion(numero1, numero2)}" // Multiplicacion
        4 -> "$numero1 / $numero2 = ${division(numero1, numero2)}" // Division
        else -> "Error: opción no reconocida"
    }
}

fun main() {
    // Llama a la función para mostrar el menú de opciones
    mostrarMenu()

    // Lee la opción y la convierte a entero
    val opcion = readlnOrNull()?.toIntOrNull() ?: 0

    // Si la opción es válida (entre 1 y 4) // Lee el primer número
    if (opcion in 1..4) {
        print("\nIngrese el primer número: ")
        val numero1 = readlnOrNull()?.toDoubleOrNull()

        print("Ingrese el segundo número: ")
        val numero2 = readlnOrNull()?.toDoubleOrNull()

        // Verifica si los números ingresados son válidos
        if (numero1 != null && numero2 != null) {
            println(calculadora(opcion, numero1, numero2)) // Llama a la función calculadora y muestra el resultado
        } else {
            println("Entrada no válida o vacia") // Mensaje de error si la entrada es inválida
        }
    } else if (opcion == 5) { // Si el usuario elige salir
        println("\nSaliendo de la calculadora...")
    } else {
        println("\nOpción no reconocida..") // Mensaje de error si la opción no es válida
    }
}