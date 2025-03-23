package `Practica 2 - Programacion Orientada a Objetos Kotlin`

/*
    Autor: Rodrigo Emerson Infanzon Acosta
    Curso: Programacion De Dispositivos Moviles
    Semestre: VI
    Fecha: 22/03/2025
    Ultima modificacion: 22/03/2025 23:41pm
*/

/*
    Sistema de Gestión de Biblioteca: Diseña un sistema de gestión de biblioteca que incluya las siguientes clases e interfaces:

    - Material: Una clase base abstracta que tiene propiedades como título, autor, anioPublicacion,  y un metodo abstracto para mostrarDetalles().
    - Libro: Una subclase de Material que tiene propiedades adicionales como género, número páginas y un metodo para mostrar los detalles del libro.
    - Revista: Una subclase de Material que tiene propiedades como issn, volumen, número, editorial y un metodo para mostrar los detalles de la revista.
    - Usuario: Una data class que tiene propiedades como nombre, apellido, edad.
    - IBiblioteca:Una interfaz que contenga los métodos registrar material, registrar usuario, préstamo, devolución,
    mostrar materiales disponibles y mostrar materiales reservados por usuario.
    - Biblioteca: Una clase que gestiona las operaciones de préstamo y devolución. Debe implementar la interfaz anterior.
    Debe tener una lista de materiales disponibles, así como un Map de usuarios donde la clase es el usuario y el valor una lista de materiales que tiene en préstamo.

    Crear instancias de las subclases y ejecutar las operaciones de préstamo y devolución para cada instancia.
*/

// Clase base abstracta que representa cualquier material en la biblioteca
abstract class Material(
    val titulo: String,
    val autor: String,
    val anioPublicacion: Int
) {
    // Metodo abstracto que cada subclase debe implementar para mostrar sus detalles
    abstract fun mostrarDetalles()
}

// Subclase Libro con propiedades adicionales
class Libro(
    titulo: String,
    autor: String,
    anioPublicacion: Int,
    private val genero: String,
    private val numeroPaginas: Int
) : Material(titulo, autor, anioPublicacion) {
    // Implementación del metodo para mostrar detalles específicos del libro
    override fun mostrarDetalles() {
        println("Libro: $titulo, Autor: $autor, Año: $anioPublicacion, Género: $genero, Páginas: $numeroPaginas")
    }
}

// Subclase Revista con propiedades adicionales
class Revista(
    titulo: String,
    autor: String,
    anioPublicacion: Int,
    private val issn: String,
    private val volumen: Int,
    private val numero: Int,
    private val editorial: String
) : Material(titulo, autor, anioPublicacion) {
    // Implementación del metodo para mostrar detalles específicos de la revista
    override fun mostrarDetalles() {
        println("Revista: $titulo, Autor: $autor, Año: $anioPublicacion, ISSN: $issn, Volumen: $volumen, Número: $numero, Editorial: $editorial")
    }
}

// Data class Usuario con datos básicos
data class Usuario(val nombre: String, val apellido: String, val edad: Int)

// Interfaz que define los métodos principales de la biblioteca
interface IBiblioteca {
    fun registrarMaterial(material: Material) // Agrega un material a la biblioteca
    fun registrarUsuario(usuario: Usuario) // Registra un nuevo usuario en el sistema
    fun prestarMaterial(usuario: Usuario, material: Material) // Presta un material a un usuario
    fun devolverMaterial(usuario: Usuario, material: Material) // Devuelve un material prestado
    fun mostrarMaterialesDisponibles() // Muestra la lista de materiales disponibles
    fun mostrarMaterialesReservadosPorUsuario(usuario: Usuario) // Muestra los materiales en préstamo de un usuario
}

// Implementación de la biblioteca
class Biblioteca : IBiblioteca {
    private val materialesDisponibles = mutableListOf<Material>() // Lista de materiales disponibles
    private val usuarios = mutableMapOf<Usuario, MutableList<Material>>() // Mapa de usuarios con sus materiales prestados

    // Agrega un material a la lista de materiales disponibles
    override fun registrarMaterial(material: Material) {
        materialesDisponibles.add(material)
        println("Material registrado: ${material.titulo}")
    }

    // Registra un usuario si no existe en el sistema
    override fun registrarUsuario(usuario: Usuario) {
        if (!usuarios.containsKey(usuario)) {
            usuarios[usuario] = mutableListOf()
            println("Usuario registrado: ${usuario.nombre} ${usuario.apellido}")
        } else {
            println("El usuario ya está registrado.")
        }
    }

    // Presta un material si está disponible y lo asigna a un usuario
    override fun prestarMaterial(usuario: Usuario, material: Material) {
        if (materialesDisponibles.contains(material)) {
            materialesDisponibles.remove(material)
            usuarios[usuario]?.add(material)
            println("Material prestado: ${material.titulo} a ${usuario.nombre} ${usuario.apellido}")
        } else {
            println("Material no disponible para préstamo.")
        }
    }

    // Devuelve un material prestado por un usuario y lo vuelve a hacer disponible
    override fun devolverMaterial(usuario: Usuario, material: Material) {
        if (usuarios[usuario]?.contains(material) == true) {
            usuarios[usuario]?.remove(material)
            materialesDisponibles.add(material)
            println("Material devuelto: ${material.titulo} por ${usuario.nombre} ${usuario.apellido}")
        } else {
            println("El usuario no tiene prestado este material.")
        }
    }

    // Muestra todos los materiales disponibles en la biblioteca
    override fun mostrarMaterialesDisponibles() {
        println("Materiales disponibles:")
        if (materialesDisponibles.isEmpty()) {
            println("No hay materiales disponibles.")
        } else {
            materialesDisponibles.forEach { it.mostrarDetalles() }
        }
    }

    // Muestra los materiales que un usuario tiene en préstamo
    override fun mostrarMaterialesReservadosPorUsuario(usuario: Usuario) {
        println("Materiales reservados por ${usuario.nombre} ${usuario.apellido}:")
        val materiales = usuarios[usuario]
        if (materiales.isNullOrEmpty()) {
            println("No tiene materiales en préstamo.")
        } else {
            materiales.forEach { it.mostrarDetalles() }
        }
    }
}

// Función principal para probar el sistema
fun main() {
    val biblioteca = Biblioteca()

    // Creando materiales
    val libro1 = Libro("1984", "George Orwell", 1949, "Distopía", 328)
    val revista1 = Revista("National Geographic", "Varios", 2024, "1234-5678", 102, 5, "NatGeo Publishing")

    // Creando usuarios
    val usuario1 = Usuario("Carlos", "Gómez", 25)

    // Registrando materiales y usuarios
    biblioteca.registrarMaterial(libro1)
    biblioteca.registrarMaterial(revista1)
    biblioteca.registrarUsuario(usuario1)

    // Mostrando materiales disponibles
    biblioteca.mostrarMaterialesDisponibles()

    // Prestando un material
    biblioteca.prestarMaterial(usuario1, libro1)
    biblioteca.mostrarMaterialesDisponibles()
    biblioteca.mostrarMaterialesReservadosPorUsuario(usuario1)

    // Devolviendo el material
    biblioteca.devolverMaterial(usuario1, libro1)
    biblioteca.mostrarMaterialesDisponibles()
    biblioteca.mostrarMaterialesReservadosPorUsuario(usuario1)
}