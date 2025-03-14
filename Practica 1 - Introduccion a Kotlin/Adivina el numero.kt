package `Practica 1 - Introduccion a Kotlin`

/*
    Autor: Rodrigo Emerson Infanzon Acosta
    Curso: Programacion De Dispositivos Moviles
    Semestre: VI
    Fecha: 14/03/2025
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

// Funcion para evaluar al empleado: entrada el salario y la puntuacion
fun evaluarEmpleados(salario: Double, puntuacion: Int): String {
    // Calcular el dinero adicional basado en la formula de puntuacion
    val adicional: Double = salario * (puntuacion / 10.0)

    // Determinar nivel de rendimiento de acuerdo a la puntuacion
    val nivel = when (puntuacion) {
        in 0..3 -> "Inaceptable" // 0, 1, 2, 3
        in 4..6 -> "Aceptable" // 4, 5, 6
        in 7..10 -> "Meritorio" // 7, 8, 9, 10
        else -> "Desconocido" // Control de error: menores que 0 o mayores a 10
    }

    // Retornar el resultado en un solo mensaje
    return "Nivel de Rendimiento: $nivel, Cantidad de Dinero Recibido: $${"%.2f".format(adicional)}"
}

fun main() {

    // Evaluar a un empleado
    println(evaluarEmpleados(10000.0,8 ))
}