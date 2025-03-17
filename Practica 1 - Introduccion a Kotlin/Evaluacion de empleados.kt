package `Practica 1 - Introduccion a Kotlin`

/*
    Autor: Rodrigo Emerson Infanzon Acosta
    Curso: Programacion De Dispositivos Moviles
    Semestre: VI
    Fecha: 14/03/2025
    Ultima modificacion: 14/03/2025 11:02 am
*/

/*
    Descripción del programa:

    Este programa evalúa el desempeño de los empleados de una empresa según una puntuación de 0 a 10,
    determinando su nivel de rendimiento y la bonificación que recibirán.
    El cálculo se realiza multiplicando el salario mensual por la puntuación dividida entre 10.
    Luego, se muestra el nivel correspondiente (Inaceptable, Aceptable o Meritorio) y la cantidad de dinero obtenida.

    -------------------------------
    |   Nivel        | Puntuación |
    -------------------------------
    | Inaceptable   |   0 - 3     |
    | Aceptable     |   4 - 6     |
    | Meritorio     |   7 - 10    |
    -------------------------------

    Ejemplo: Salario 10,000; Puntuación 8. Dinero = 10,000 * (8/10)= 8000.
    Resultado: Nivel de Rendimiento Meritorio, Cantidad de Dinero Recibido $8000
*/

// Funcion que evalua que la puntuacion este dentro del rango
fun evaluarRango(puntuacion: Int): Boolean {
    return puntuacion in 0..10
}

// Funcion para evaluar al empleado: entrada el salario y la puntuacion
fun evaluarEmpleados(salario: Double, puntuacion: Int): String {
    // Si la puntuación es inválida, establecerla en "Desconocido" y el dinero en 0
    if (!evaluarRango(puntuacion)) {
        return "Nivel de Rendimiento: Desconocido, Cantidad de Dinero Recibido: $0.00"
    }

    // Calcular el dinero adicional basado en la fórmula de puntuación
    val adicional: Double = salario * (puntuacion / 10.0)

    // Determinar nivel de rendimiento de acuerdo a la puntuación del empleado
    val nivel = when (puntuacion) {
        in 0..3 -> "Inaceptable"
        in 4..6 -> "Aceptable"
        in 7..10 -> "Meritorio"
        else -> "Desconocido"
    }

    // Retornar el resultado en el formato solicitado
    return "Nivel de Rendimiento: $nivel, Cantidad de Dinero Recibido: $${"%.2f".format(adicional)}"
}

fun main() {
    // Evaluar a un empleado
    println(evaluarEmpleados(10000.0,11))
}