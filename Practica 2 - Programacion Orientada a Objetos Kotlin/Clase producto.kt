package `Practica 2 - Programacion Orientada a Objetos Kotlin`

/*
    Autor: Rodrigo Emerson Infanzon Acosta
    Curso: Programacion De Dispositivos Moviles
    Semestre: VI
    Fecha: 22/03/2025
    Ultima modificacion: 22/03/2025 23:29 pm
*/

/*
    Diseña una clase Producto que tenga un precio y un descuento aplicable.
    Implementa métodos set y get para establecer y obtener el precio y el descuento,
    y añade un metodo para calcular el precio final después de aplicar el descuento.
    Utilice el set para validar datos.
*/

// Definición de la clase Producto, que representa un producto con precio y descuento
class Producto(
    private var precio: Float,  // Precio del producto
    private var descuento: Int   // Descuento en porcentaje (0-100)
) {

    // Bloque de inicialización para validar valores iniciales
    init {
        if (precio <= 0) {
            println("El precio debe ser mayor que 0. Se establecerá en 1 por defecto.")
            precio = 1f
        }
        if (descuento !in 0..100) {
            println("El descuento debe estar entre 0 y 100. Se establecerá en 0 por defecto.")
            descuento = 0
        }
    }

    // Metodo para modificar el precio con validación
    fun setPrecio(nuevoPrecio: Float) {
        if (nuevoPrecio > 0) {
            precio = nuevoPrecio
            println("Nuevo precio establecido: $precio")
        } else {
            println("El precio no puede ser negativo ni cero.")
        }
    }

    // Metodo para modificar el descuento con validación
    fun setDescuento(nuevoDescuento: Int) {
        if (nuevoDescuento in 0..100) {
            descuento = nuevoDescuento
            println("Nuevo descuento establecido: $descuento%")
        } else {
            println("Descuento inválido. Debe estar entre 0 y 100.")
        }
    }

    // Metodo para obtener el precio actual
    fun getPrecio(): Float = precio

    // Metodo para obtener el descuento actual
    fun getDescuento(): Int = descuento

    // Metodo para calcular el precio final después del descuento
    fun calcularPrecioFinal(): Float {
        return precio * (1 - descuento / 100f)
    }

    // Metodo para imprimir la información del producto
    fun imprimirInformacion() {
        println("Información del producto:")
        println("Precio actual: $precio")
        println("Descuento aplicado: $descuento%")
        println("Precio final con descuento: ${calcularPrecioFinal()}\n")
    }
}

// Función principal para probar la clase Producto
fun main() {
    // Crear un producto con precio de 100 y descuento de 10%
    val producto = Producto(100f, 10)

    // Imprimir la información inicial del producto
    producto.imprimirInformacion()

    // Modificar el precio y el descuento
    producto.setPrecio(200f)
    producto.setDescuento(15)

    // Imprimir la información después de actualizar los valores
    producto.imprimirInformacion()

    // Intentar asignar valores inválidos para verificar validaciones
    //producto.setPrecio(-50f)
    producto.setDescuento(120)

    // Imprimir la información final del producto
    producto.imprimirInformacion()
}