package `Practica 2 - Programacion Orientada a Objetos Kotlin`

/*
    Autor: Rodrigo Emerson Infanzon Acosta
    Curso: Programacion De Dispositivos Moviles
    Semestre: VI
    Fecha: 20/03/2025
    Ultima modificacion: 20/03/2025 14:16 pm
*/

/*
    Diseña una clase CuentaBancaria que tenga un saldo y un límite de retiro.
    Implementa métodos set y get para establecer y obtener el saldo,
    y añade un metodo para realizar retiros que tenga en cuenta el límite de retiro.
    Utilice el set para validar datos.
*/

class CuentaBancaria(private var saldo: Number, private var limiteRetiro: Number) {

    // Obtener el saldo actual
    fun getSaldo(): Number { return saldo }

    // Modificar el saldo
    fun setSaldo(nuevoSaldo: Number) {
        if (nuevoSaldo.toDouble() >= 0) {
            saldo = nuevoSaldo
        } else {
            println("No puede existir un saldo negativo\n")
        }
    }

    // Obtener el limite de un retiro
    fun getLimiteRetiro(): Number {
        return limiteRetiro
    }

    // Modificar el limite de un retiro
    fun setLimiteRetiro(nuevoLimite: Number) {
        if (nuevoLimite.toDouble() > 0) {
            limiteRetiro = nuevoLimite
        } else {
            println("El limite de retiro debe ser mayor a 0\n")
        }
    }

    // Metodo para realizar un retiro
    fun retirar(monto: Number) {
        val montoDouble = monto.toDouble()
        val saldoDouble = saldo.toDouble()
        val limiteDouble = limiteRetiro.toDouble()

        when {
            montoDouble > saldoDouble -> {
                val faltante = montoDouble - saldoDouble
                println("Saldo insuficiente")
                println("Falta $faltante para completar el retiro\n")
            }
            montoDouble > limiteDouble -> {
                println("El monto excede el límite de retiro.")
                println("Se permite hasta $limiteDouble\n")
            }
            else -> {
                saldo = saldoDouble - montoDouble
                println("Retiro exitoso!!!")
                println("Saldo actualizado de la cuenta: $saldo\n")
            }
        }
    }
}


fun main() {
    val cuenta = CuentaBancaria(5000, 500)

    println("Saldo inicial: ${cuenta.getSaldo()}")

    cuenta.retirar(501)

    cuenta.retirar(200)

}