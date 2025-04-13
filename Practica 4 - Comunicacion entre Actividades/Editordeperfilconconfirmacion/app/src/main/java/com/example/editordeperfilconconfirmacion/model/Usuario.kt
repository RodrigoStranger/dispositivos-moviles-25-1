package com.example.editordeperfilconconfirmacion.model

import android.os.Parcel
import android.os.Parcelable

// Hacemos que la clase Usuario implemente Parcelable
data class Usuario(
    val nombre: String,
    val edad: String,
    val ciudad: String,
    val correo: String
) : Parcelable {

    // Constructor que permite crear el objeto desde un Parcel
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    // Metodo que empaqueta el objeto en un Parcel para enviarlo
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(edad)
        parcel.writeString(ciudad)
        parcel.writeString(correo)
    }

    // Este metodo devuelve un valor constante que se utiliza para crear un array de objetos Parcelable
    override fun describeContents(): Int {
        return 0
    }

    // Este objeto es necesario para la deserialización del objeto Parcelable
    companion object CREATOR : Parcelable.Creator<Usuario> {
        override fun createFromParcel(parcel: Parcel): Usuario {
            return Usuario(parcel)
        }

        override fun newArray(size: Int): Array<Usuario?> {
            return arrayOfNulls(size)
        }
    }
}
