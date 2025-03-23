package `Practica 2 - Programacion Orientada a Objetos Kotlin`

/*
    Autor: Rodrigo Emerson Infanzon Acosta
    Curso: Programacion De Dispositivos Moviles
    Semestre: VI
    Fecha: 22/03/2025
    Ultima modificacion: 22/03/2025 23:19 pm
*/

/*
    Objetivo:
    - Crear una clase abstracta "Shape" que represente figuras geometricas con propiedades de area y perimetro.
    - Implementar subclases especificas: Cuadrado, Rectangulo y Circulo.
    - Cada subclase debe definir sus propios calculos de area y perimetro.
    - Instanciar estas figuras y mostrar sus atributos antes de calcular los resultados.
    - Mostrar los resultados despues de realizar los calculos.
*/

// Definición de la clase abstracta Shape, que representa cualquier figura geométrica
abstract class Shape {
    // Propiedades protegidas para almacenar área y perímetro
    protected var area: Number = 0.0
    protected var perimetro: Number = 0.0

    // Métodos abstractos: Obligan a las subclases a definir cómo calcular área y perímetro
    abstract fun calcularArea()
    abstract fun calcularPerimetro()

    // Método común para imprimir los resultados de cada figura
    fun imprimirResultados(nombre: String) {
        println("Resultados del $nombre")
        println("Área: $area")
        println("Perímetro: $perimetro\n")
    }
}

// Subclase Cuadrado: Representa un cuadrado y hereda de Shape
class Cuadrado(private var lado: Number) : Shape() {

    // Bloque init para validar que el lado sea mayor que 0, si no se asigna un valor predeterminado
    init {
        if (lado.toDouble() <= 0) {
            println("Lado inválido. Se asignará 1 por defecto.\n")
            lado = 1
        }
    }

    // Implementación del cálculo del área (lado * lado)
    override fun calcularArea() {
        area = lado.toDouble() * lado.toDouble()
    }

    // Implementación del cálculo del perímetro (4 * lado)
    override fun calcularPerimetro() {
        perimetro = 4 * lado.toDouble()
    }

    // Metodo para imprimir los atributos del cuadrado
    fun imprimirAtributos() {
        println("Atributos del Cuadrado:")
        println("Lado: $lado")
    }
}

// Subclase Rectángulo: Representa un rectángulo y hereda de Shape
class Rectangulo(private var base: Number, private var altura: Number) : Shape() {

    // Bloque init para validar que la base y la altura sean mayores que 0
    init {
        if (base.toDouble() <= 0) {
            println("Base inválida. Se asignará 1 por defecto.\n")
            base = 1
        }
        if (altura.toDouble() <= 0) {
            println("Altura inválida. Se asignará 1 por defecto.\n")
            altura = 1
        }
    }

    // Implementación del cálculo del área (base * altura)
    override fun calcularArea() {
        area = base.toDouble() * altura.toDouble()
    }

    // Implementación del cálculo del perímetro (2 * (base + altura))
    override fun calcularPerimetro() {
        perimetro = 2 * (base.toDouble() + altura.toDouble())
    }

    // Método para imprimir los atributos del rectángulo
    fun imprimirAtributos() {
        println("Atributos del Rectángulo:")
        println("Base: $base")
        println("Altura: $altura")
    }
}

// Subclase Círculo: Representa un círculo y hereda de Shape
class Circulo(private var radio: Number) : Shape() {

    // Bloque init para validar que el radio sea mayor que 0
    init {
        if (radio.toDouble() <= 0) {
            println("Radio inválido. Se asignará 1 por defecto.\n")
            radio = 1
        }
    }

    // Implementación del cálculo del área (π * radio^2)
    override fun calcularArea() {
        area = Math.PI * radio.toDouble() * radio.toDouble()
    }

    // Implementación del cálculo del perímetro (2 * π * radio)
    override fun calcularPerimetro() {
        perimetro = 2 * Math.PI * radio.toDouble()
    }

    // Método para imprimir los atributos del círculo
    fun imprimirAtributos() {
        println("Atributos del Círculo:")
        println("Radio: $radio")
    }
}

// Función principal donde se crean instancias de las figuras y se ejecutan las operaciones
fun main() {
    // Creación de un cuadrado con un lado válido
    val cuadrado = Cuadrado(4)
    cuadrado.imprimirAtributos() // Muestra los atributos
    cuadrado.calcularArea()
    cuadrado.calcularPerimetro()
    cuadrado.imprimirResultados("Cuadrado") // Muestra el área y el perímetro

    // Creación de un rectángulo con valores válidos
    val rectangulo = Rectangulo(5, 3)
    rectangulo.imprimirAtributos()
    rectangulo.calcularArea()
    rectangulo.calcularPerimetro()
    rectangulo.imprimirResultados("Rectángulo")

    // Creación de un círculo con un radio válido
    val circulo = Circulo(2.5)
    circulo.imprimirAtributos()
    circulo.calcularArea()
    circulo.calcularPerimetro()
    circulo.imprimirResultados("Círculo")
}