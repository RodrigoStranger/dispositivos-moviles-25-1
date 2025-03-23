package `Practica 2 - Programacion Orientada a Objetos Kotlin`

/*
    Autor: Rodrigo Emerson Infanzon Acosta
    Curso: Programacion De Dispositivos Moviles
    Semestre: VI
    Fecha: 20/03/2025
    Ultima modificacion: 22/03/2025 22:56 pm
*/

/*
    Diseña una clase CuentaBancaria que tenga un saldo y un límite de retiro.
    Implementa métodos set y get para establecer y obtener el saldo,
    y añade un metodo para realizar retiros que tenga en cuenta el límite de retiro.
    Utilice el set para validar datos.
*/

// Definición de la clase CuentaBancaria con constructor primario
class CuentaBancaria(
    private var saldo: Number,         // Saldo de la cuenta (privado)
    private var limiteRetiro: Number   // Límite de retiro permitido (privado)
) {
    // Bloque de inicialización para validar los valores iniciales del saldo y límite de retiro
    init {
        require(saldo.toDouble() >= 0) { "El saldo no puede ser negativo." }  // Verifica que el saldo sea >= 0
        require(limiteRetiro.toDouble() > 0) { "El límite de retiro debe ser mayor que 0." }  // Verifica que el límite > 0
    }

    // Propiedad para obtener y modificar el saldo con validaciones
    var saldoActual: Number
        get() = saldo  // Obtiene el saldo actual
        set(value) {
            require(value.toDouble() >= 0) { "El saldo no puede ser negativo." }  // Verifica que el nuevo saldo sea >= 0
            if (value.toDouble() == saldo.toDouble()) {
                println("El nuevo saldo es el mismo que el actual: $saldo\n")  // Si el saldo no cambia, se informa
            } else {
                saldo = value  // Se actualiza el saldo
                println("Saldo actualizado correctamente a: $saldo\n")  // Se confirma el cambio
            }
        }

    // Propiedad para obtener y modificar el límite de retiro con validaciones
    var limiteDeRetiro: Number
        get() = limiteRetiro  // Obtiene el límite de retiro actual
        set(value) {
            require(value.toDouble() > 0) { "El límite de retiro debe ser mayor a 0." }  // El límite debe ser > 0
            if (value.toDouble() == limiteRetiro.toDouble()) {
                println("El nuevo límite de retiro es el mismo que el actual: $limiteRetiro\n")  // Si no cambia, se informa
            } else {
                limiteRetiro = value  // Se actualiza el límite de retiro
                println("Nuevo límite de retiro establecido: $limiteRetiro\n")  // Se confirma el cambio
            }
        }

    // Método para realizar un retiro de la cuenta con validaciones
    fun retirar(monto: Number) {
        val montoDouble = monto.toDouble()  // Convertimos el monto a Double para cálculos
        when {
            // Caso 1: El monto a retirar es mayor que el saldo disponible
            montoDouble > saldo.toDouble() -> println("Saldo insuficiente. Faltan ${montoDouble - saldo.toDouble()} para completar el retiro.\n")

            // Caso 2: El monto a retirar es mayor que el límite de retiro permitido
            montoDouble > limiteRetiro.toDouble() -> println("El monto excede el límite de retiro permitido ($limiteRetiro).\n")

            // Caso 3: El retiro es válido y se descuenta del saldo
            else -> {
                saldo = saldo.toDouble() - montoDouble  // Se actualiza el saldo restando el monto retirado
                println("Retiro exitoso! Saldo actualizado: $saldo\n")  // Confirmación de retiro exitoso
            }
        }
    }
}

// Función principal
fun main() {
    // Se crea una cuenta bancaria con un saldo inicial de 500 y un límite de retiro de 500
    val cuenta = CuentaBancaria(500, 500)

    // Mostrar el saldo inicial de la cuenta
    println("Saldo inicial: ${cuenta.saldoActual}\n")

    // Pruebas modificando el saldo y el límite de retiro
    cuenta.saldoActual = 100
    cuenta.limiteDeRetiro = 200
    cuenta.retirar(150)
}