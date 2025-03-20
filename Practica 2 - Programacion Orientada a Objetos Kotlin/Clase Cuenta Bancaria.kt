package `Practica 2 - Programacion Orientada a Objetos Kotlin`

/*
    Autor: Rodrigo Emerson Infanzon Acosta
    Curso: Programacion De Dispositivos Moviles
    Semestre: VI
    Fecha: 20/03/2025
    Ultima modificacion: 20/03/2025 14:33 pm
*/

/*
    Diseña una clase CuentaBancaria que tenga un saldo y un límite de retiro.
    Implementa métodos set y get para establecer y obtener el saldo,
    y añade un metodo para realizar retiros que tenga en cuenta el límite de retiro.
    Utilice el set para validar datos.
*/

// Definición de la clase CuentaBancaria
// Se utiliza Number para que los valores puedan ser enteros (Int) o decimales (Float, Double).
class CuentaBancaria(private var saldo: Number, private var limiteRetiro: Number) {

    // Metodo para obtener el saldo actual de la cuenta
    fun getSaldo(): Number { return saldo }

    // Metodo para modificar el saldo de la cuenta con validación
    fun setSaldo(nuevoSaldo: Number) {
        // Convertimos el nuevo saldo y el saldo actual a Double para evitar problemas con diferentes tipos de datos
        val nuevoSaldoDouble = nuevoSaldo.toDouble()
        val saldoActualDouble = saldo.toDouble()

        // Verificamos si el nuevo saldo es negativo
        if (nuevoSaldoDouble < 0) {
            println("No puede existir un saldo negativo\n") // Mensaje de error si el saldo es negativo
        }
        // Verificamos si el nuevo saldo es igual al saldo actual
        else if (nuevoSaldoDouble == saldoActualDouble) {
            println("El nuevo saldo es el mismo que el saldo actual") // Mensaje indicando que no hay cambios
            println("Saldo actual: $saldo\n")
        }
        // Si el nuevo saldo es válido y diferente, lo actualizamos
        else {
            saldo = nuevoSaldo
            println("Saldo actualizado correctamente a: $saldo\n") // Confirmación de que el saldo fue cambiado
        }
    }

    // Metodo para obtener el límite de retiro actual
    fun getLimiteRetiro(): Number { return limiteRetiro }

    // Metodo para modificar el límite de retiro con validación
    fun setLimiteRetiro(nuevoLimite: Number) {
        val nuevoLimiteDouble = nuevoLimite.toDouble()
        val limiteActualDouble = limiteRetiro.toDouble()

        if (nuevoLimiteDouble <= 0) { // No puede existir un limite negativo
            println("El límite de retiro debe ser mayor a 0\n")
        } else if (nuevoLimiteDouble == limiteActualDouble) {
            println("El nuevo límite de retiro es el mismo que el actual")
            println("Límite de retiro establecido: $limiteRetiro\n")
        } else {
            limiteRetiro = nuevoLimite
            println("Nuevo límite de retiro establecido: $limiteRetiro\n")
        }
    }

    // Metodo para realizar un retiro de la cuenta
    fun retirar(monto: Number) {
        val montoDouble = monto.toDouble()  // Convertimos el monto a Double para cálculos
        val saldoDouble = saldo.toDouble()  // Convertimos el saldo a Double
        val limiteDouble = limiteRetiro.toDouble()  // Convertimos el límite de retiro a Double

        when {
            // Caso 1: El monto a retirar es mayor que el saldo disponible
            montoDouble > saldoDouble -> {
                val faltante = montoDouble - saldoDouble  // Calculamos cuánto falta para cubrir el retiro
                println("Saldo insuficiente")
                println("Faltan $faltante para completar el retiro\n")
            }
            // Caso 2: El monto a retirar es mayor que el límite de retiro permitido
            montoDouble > limiteDouble -> {
                println("El monto excede el límite de retiro.")
                println("Se permite hasta $limiteDouble por cada operación de retiro\n")
            }
            // Caso 3: El retiro es válido
            else -> {
                saldo = saldoDouble - montoDouble  // Se actualiza el saldo restando el monto retirado
                println("Retiro exitoso!!!")
                println("Saldo actualizado de la cuenta: $saldo\n")
            }
        }
    }
}

// Función principal para probar la clase CuentaBancaria
fun main() {
    // Crear una cuenta bancaria con un saldo inicial de 500 y un límite de retiro de 500
    val cuenta = CuentaBancaria(500, 500)

    // Mostrar el saldo inicial
    println("Saldo inicial de la cuenta: ${cuenta.getSaldo()}\n")

    //Pruebas en general (modificables)
    cuenta.setSaldo(100)
}