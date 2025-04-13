package com.example.editordeperfilconconfirmacion.model

// Importamos las clases necesarias para implementar Parcelable
import android.os.Parcel
import android.os.Parcelable

// Definimos una clase de datos 'Usuario', que implementa la interfaz 'Parcelable'
// 'Parcelable' es una interfaz que permite pasar objetos entre actividades de manera eficiente.
data class Usuario(
    val nombre: String, // Atributo para almacenar el nombre del usuario
    val edad: String,   // Atributo para almacenar la edad del usuario
    val ciudad: String, // Atributo para almacenar la ciudad del usuario
    val correo: String  // Atributo para almacenar el correo electrónico del usuario
) : Parcelable { // Declaramos que la clase implementa Parcelable

    // Constructor secundario para crear el objeto Usuario desde un 'Parcel'
    // Un 'Parcel' es un contenedor utilizado para pasar datos entre actividades
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",  // Leemos el nombre del usuario desde el Parcel, con un valor por defecto de ""
        parcel.readString() ?: "",  // Leemos la edad del usuario desde el Parcel, con un valor por defecto de ""
        parcel.readString() ?: "",  // Leemos la ciudad del usuario desde el Parcel, con un valor por defecto de ""
        parcel.readString() ?: ""   // Leemos el correo del usuario desde el Parcel, con un valor por defecto de ""
    )

    // Metodo 'writeToParcel' que escribe los datos del objeto Usuario en un 'Parcel'
    // Esto es necesario para que el objeto sea fácilmente transferible entre actividades.
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)  // Escribimos el nombre del usuario en el Parcel
        parcel.writeString(edad)   // Escribimos la edad del usuario en el Parcel
        parcel.writeString(ciudad) // Escribimos la ciudad del usuario en el Parcel
        parcel.writeString(correo) // Escribimos el correo del usuario en el Parcel
    }

    // Este metodo describe el contenido del objeto. Generalmente se retorna 0.
    override fun describeContents(): Int = 0

    // La clave para hacer que el objeto Usuario sea Parcelable es este 'CREATOR'.
    // El 'CREATOR' se utiliza para convertir el Parcel de vuelta en un objeto Usuario.
    companion object {
        @JvmField
        @Suppress("UNUSED_PROPERTY") // Esta anotación es para evitar advertencias de Android Studio
        val CREATOR: Parcelable.Creator<Usuario> = object : Parcelable.Creator<Usuario> {
            // Metodo para crear una instancia de Usuario a partir de un Parcel
            override fun createFromParcel(parcel: Parcel): Usuario {
                return Usuario(parcel) // Llamamos al constructor que toma un Parcel
            }

            // Metodo para crear un array de objetos Usuario
            override fun newArray(size: Int): Array<Usuario?> {
                return arrayOfNulls(size) // Devolvemos un array de Usuario vacío de tamaño 'size'
            }
        }
    }
}