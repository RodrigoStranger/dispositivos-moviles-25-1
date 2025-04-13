package com.example.editordeperfilconconfirmacion

/*
    Autor: Rodrigo Emerson Infanzon Acosta
    Curso: Programacion De Dispositivos Moviles
    Semestre: VI
    Fecha: 13/04/2025
    Ultima modificacion: 13/04/2025 15:52pm
*/

/*
     Crear una app que permita llenar un perfil de usuario,
     mostrar los datos en otra pantalla y confirmar si está correcto.

     Actividad 1 – FormularioActivity:
     - Cuatro campos para: Nombre, Edad, Ciudad, y Correo electrónico.
     - Un botón que diga "Continuar".
     - Al presionarlo, se envían los datos a la segunda actividad

     Actividad 2 – ResumenActivity
    Muestra un resumen de los datos escritos.
    Tiene dos botones:
    - "Confirmar" ( vuelve a la primera pantalla y aparece un Toast que diga "Perfil guardado correctamente).
    - "Volver a editar" (vuelve a la pantalla anterior para seguir editando información).
*/

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.editordeperfilconconfirmacion.model.Usuario

class FormularioActivity : AppCompatActivity() {

    // Variables para el formulario
    private lateinit var editTextNombre: EditText
    private lateinit var editTextEdad: EditText
    private lateinit var editTextCiudad: EditText
    private lateinit var editTextCorreo: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.formulario_activity)

        // Inicializamos los campos de texto
        editTextNombre = findViewById(R.id.editTextNombre)
        editTextEdad = findViewById(R.id.editTextEdad)
        editTextCiudad = findViewById(R.id.editTextCiudad)
        editTextCorreo = findViewById(R.id.editTextCorreo)
        val btnContinuar: Button = findViewById(R.id.btnContinuar)

        btnContinuar.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            val edad = editTextEdad.text.toString()
            val ciudad = editTextCiudad.text.toString()
            val correo = editTextCorreo.text.toString()

            // Verificar si los campos están vacíos
            if (nombre.isEmpty() || edad.isEmpty() || ciudad.isEmpty() || correo.isEmpty()) {
                Toast.makeText(this, getString(R.string.error_campos_vacios), Toast.LENGTH_SHORT).show()
            } else if (!validarEdad(edad)) {
                Toast.makeText(this, getString(R.string.error_edad_invalida), Toast.LENGTH_SHORT).show()
            } else if (!validarNombreCiudad(nombre)) {
                Toast.makeText(this, getString(R.string.error_nombre_invalido), Toast.LENGTH_SHORT).show()
            } else if (!validarNombreCiudad(ciudad)) {
                Toast.makeText(this, getString(R.string.error_ciudad_invalida), Toast.LENGTH_SHORT).show()
            } else if (!validarCorreo(correo)) {
                Toast.makeText(this, getString(R.string.error_correo_invalido), Toast.LENGTH_SHORT).show()
            } else {
                val usuario = Usuario(nombre, edad, ciudad, correo)
                val intent = Intent(this, ResumenActivity::class.java)

                // Pasar el objeto Usuario al Intent
                intent.putExtra("usuario", usuario)

                // Lanzar ResumenActivity y esperar el resultado
                startActivityForResult(intent, 1) // '1' es el código de solicitud
            }
        }
    }

    // Metodo onActivityResult para recibir los resultados de ResumenActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK) {
            val usuario = data?.getParcelableExtra<Usuario>("usuario")

            if (usuario != null) {
                // Rellenamos los campos con los datos recibidos
                editTextNombre.setText(usuario.nombre)
                editTextEdad.setText(usuario.edad)
                editTextCiudad.setText(usuario.ciudad)
                editTextCorreo.setText(usuario.correo)

                Toast.makeText(this, R.string.perfil_guardado, Toast.LENGTH_SHORT).show()

                // Limpiamos los campos para preparar un nuevo formulario
                editTextNombre.text.clear()
                editTextEdad.text.clear()
                editTextCiudad.text.clear()
                editTextCorreo.text.clear()
            }
        }
    }

    private fun validarEdad(edad: String): Boolean {
        return try {
            val edadInt = edad.toInt()
            edadInt in 1..99
        } catch (_: NumberFormatException) {
            false
        }
    }

    private fun validarCorreo(correo: String): Boolean {
        val regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        return correo.matches(regex.toRegex())
    }

    private fun validarNombreCiudad(text: String): Boolean {
        // Expresión regular para verificar que no contenga números
        val regex = "^[a-zA-Z ]+$"
        return text.matches(regex.toRegex())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Guardamos los valores de los campos en el estado de la actividad
        outState.putString(getString(R.string.key_nombre), editTextNombre.text.toString())
        outState.putString(getString(R.string.key_edad), editTextEdad.text.toString())
        outState.putString(getString(R.string.key_ciudad), editTextCiudad.text.toString())
        outState.putString(getString(R.string.key_correo), editTextCorreo.text.toString())
    }
}