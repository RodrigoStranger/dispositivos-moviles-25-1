package com.example.editordeperfilconconfirmacion

/*
    Autor: Rodrigo Emerson Infanzon Acosta
    Curso: Programacion De Dispositivos Moviles
    Semestre: VI
    Fecha: 13/04/2025
    Ultima modificacion: 13/04/2025 15:09pm
*/

/*
     Crear una app que permita llenar un perfil de usuario,
     mostrar los datos en otra pantalla y confirmar si está correcto.

     Actividad 1 – FormularioActivity:
     - Cuatro campos para: Nombre, Edad, Ciudad, y Correo electrónico.
     - Un botón que diga “Continuar”.
     - Al presionarlo, se envían los datos a la segunda actividad

     Actividad 2 – ResumenActivity
    Muestra un resumen de los datos escritos.
    Tiene dos botones:
    - “Confirmar” ( vuelve a la primera pantalla y aparece un Toast que diga “Perfil guardado correctamente).
    - “Volver a editar” (vuelve a la pantalla anterior para seguir editando información).
*/

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.editordeperfilconconfirmacion.model.Usuario

class FormularioActivity : AppCompatActivity() {

    // Declaramos las variables para los campos de texto donde el usuario ingresará los datos
    private lateinit var editTextNombre: EditText
    private lateinit var editTextEdad: EditText
    private lateinit var editTextCiudad: EditText
    private lateinit var editTextCorreo: EditText

    // Metodo onCreate que se ejecuta cuando la actividad es creada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Establecemos el layout para esta actividad, que contiene los campos de entrada del formulario
        setContentView(R.layout.formulario_activity)

        // Inicializamos las variables con las referencias de los campos de entrada en el layout
        editTextNombre = findViewById(R.id.editTextNombre)
        editTextEdad = findViewById(R.id.editTextEdad)
        editTextCiudad = findViewById(R.id.editTextCiudad)
        editTextCorreo = findViewById(R.id.editTextCorreo)

        // Referencia al botón de continuar
        val btnContinuar: Button = findViewById(R.id.btnContinuar)

        // Si la actividad se ha reiniciado (por ejemplo, debido a un cambio de orientación),
        // restauramos los valores de los campos de texto
        if (savedInstanceState != null) {
            // Usamos los recursos de strings.xml para asignar los "hint" (textos de ayuda) a los EditText
            editTextNombre.hint = getString(R.string.hint_nombre)
            editTextEdad.hint = getString(R.string.hint_edad)
            editTextCiudad.hint = getString(R.string.hint_ciudad)
            editTextCorreo.hint = getString(R.string.hint_correo)
        }

        // Configuramos la acción que ocurrirá cuando el botón "Continuar" sea presionado
        btnContinuar.setOnClickListener {
            // Obtenemos los valores ingresados en los campos de texto
            val nombre = editTextNombre.text.toString()
            val edad = editTextEdad.text.toString()
            val ciudad = editTextCiudad.text.toString()
            val correo = editTextCorreo.text.toString()

            // Validamos que todos los campos tengan datos
            if (nombre.isEmpty() || edad.isEmpty() || ciudad.isEmpty() || correo.isEmpty()) {
                // Si algún campo está vacío, mostramos un mensaje de error
                Toast.makeText(this, getString(R.string.error_campos_vacios), Toast.LENGTH_SHORT).show()
            } else if (!validarEdad(edad)) {
                // Si la edad no es válida (no está en el rango de 1 a 99), mostramos un mensaje de error
                Toast.makeText(this, getString(R.string.error_edad_invalida), Toast.LENGTH_SHORT).show()
            } else if (ciudad.matches(".*\\d.*".toRegex())) {
                // Si la ciudad contiene números, no es válida y mostramos un mensaje de error
                Toast.makeText(this, getString(R.string.error_ciudad_invalida), Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Si la ciudad no es válida, no continuamos con el proceso
            } else if (!validarCorreo(correo)) {
                // Si el correo no tiene el formato correcto, mostramos un mensaje de error
                Toast.makeText(this, getString(R.string.error_correo_invalido), Toast.LENGTH_SHORT).show()
            } else {
                // Si correcto, creamos un objeto Usuario con los datos ingresados
                val usuario = Usuario(nombre, edad, ciudad, correo)

                // Creamos un Intent para pasar los datos a la siguiente actividad
                val intent = Intent(this, ResumenActivity::class.java)
                // Pasamos el objeto Usuario a la otra actividad
                intent.putExtra(getString(R.string.key_usuario), usuario)
                startActivity(intent) // Iniciamos la nueva actividad
            }
        }
    }

    // Función para validar que la edad esté entre 1 y 99
    private fun validarEdad(edad: String): Boolean {
        return try {
            val edadInt = edad.toInt() // Convertimos el valor de la edad a entero
            edadInt in 1..99 // Verificamos si está en el rango permitido
        } catch (_: NumberFormatException) {
            // Si ocurre un error al convertir la edad a entero, retornamos false
            false
        }
    }

    // Función para validar el formato del correo electrónico
    private fun validarCorreo(correo: String): Boolean {
        // Usamos una expresión regular para validar el formato del correo
        val regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        return correo.matches(regex.toRegex()) // Retornamos si el correo cumple con la expresión regular
    }

    // Función que se llama para guardar el estado de la actividad (por ejemplo, al rotar la pantalla)
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Guardamos los valores de los campos de texto en el estado de la actividad
        outState.putString(getString(R.string.key_nombre), editTextNombre.text.toString())
        outState.putString(getString(R.string.key_edad), editTextEdad.text.toString())
        outState.putString(getString(R.string.key_ciudad), editTextCiudad.text.toString())
        outState.putString(getString(R.string.key_correo), editTextCorreo.text.toString())
    }
}