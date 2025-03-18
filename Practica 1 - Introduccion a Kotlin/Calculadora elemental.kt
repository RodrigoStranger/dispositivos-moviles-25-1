package `Practica 1 - Introduccion a Kotlin`

/*
    Autor: Rodrigo Emerson Infanzon Acosta
    Curso: Programacion De Dispositivos Moviles
    Semestre: VI
    Fecha: 14/03/2025
    Ultima modificacion: 18/03/2025 9.51 am
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

// Función para sumar dos números
// Se usa Number para aceptar tanto enteros (Int) como decimales (Double, Float)
fun suma(a: Number, b: Number): Double = a.toDouble() + b.toDouble()

// Función para restar dos números
// Convierte ambos valores a Double para asegurar precisión en cálculos mixtos (Int, Float, Double)
fun resta(a: Number, b: Number): Double = a.toDouble() - b.toDouble()

// Función para multiplicar dos números
// Permite operar con diferentes tipos numéricos sin errores de tipo
fun multiplicacion(a: Number, b: Number): Double = a.toDouble() * b.toDouble()

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

    // Lee la entrada del usuario y maneja posibles errores
    // readlnOrNull() devuelve null si la entrada está vacía o hay un error
    // toIntOrNull() convierte el valor a Int, pero si la conversión falla (por ejemplo, si el usuario ingresa letras), devuelve null
    // ?: 0 es el operador Elvis, que asigna 0 si toIntOrNull() devuelve null (para evitar errores)
    val opcion = readlnOrNull()?.toIntOrNull() ?: 0

    // Verifica si la opción ingresada está en el rango válido (1 a 4)
    if (opcion in 1..4) {
        // Pide al usuario ingresar el primer número
        print("\nIngrese el primer número: ")
        val numero1 = readlnOrNull()?.toDoubleOrNull() // Convierte la entrada a Double, o devuelve null si la conversión falla

        // Pide al usuario ingresar el segundo número
        print("Ingrese el segundo número: ")
        val numero2 = readlnOrNull()?.toDoubleOrNull() // Convierte la entrada a Double, o devuelve null si la conversión falla

        // Verifica si los números ingresados son válidos
        if (numero1 != null && numero2 != null) {
            println(calculadora(opcion, numero1, numero2)) // Llama a la función calculadora y muestra el resultado
        } else {
            println("Entrada no válida o vacía") // Mensaje de error si la entrada es inválida
        }
    } else if (opcion == 5) { // Si el usuario elige salir
        println("\nSaliendo de la calculadora...")
    } else {
        println("\nOpción no reconocida.") // Mensaje de error si la opción no es válida
    }
}