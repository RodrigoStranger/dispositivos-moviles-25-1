package com.example.editordeperfilconconfirmacion

/*
    Autor: Rodrigo Emerson Infanzon Acosta
    Curso: Programacion De Dispositivos Moviles
    Semestre: VI
    Fecha: 13/04/2025
    Ultima modificacion: 13/04/2025 12:53pm
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

class FormActivity : AppCompatActivity() {

    private lateinit var editTextNombre: EditText
    private lateinit var editTextEdad: EditText
    private lateinit var editTextCiudad: EditText
    private lateinit var editTextCorreo: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_activity)

        // Obtener las referencias a los campos y el botón
        editTextNombre = findViewById(R.id.editTextNombre)
        editTextEdad = findViewById(R.id.editTextEdad)
        editTextCiudad = findViewById(R.id.editTextCiudad)
        editTextCorreo = findViewById(R.id.editTextCorreo)
        val btnContinuar: Button = findViewById(R.id.btnContinuar)

        // Si hay un estado guardado, restaurar los valores
        if (savedInstanceState != null) {
            editTextNombre.setText(savedInstanceState.getString("nombre"))
            editTextEdad.setText(savedInstanceState.getString("edad"))
            editTextCiudad.setText(savedInstanceState.getString("ciudad"))
            editTextCorreo.setText(savedInstanceState.getString("correo"))
        }

        // Acción cuando el botón "Continuar" es presionado
        btnContinuar.setOnClickListener {
            // Obtener los valores de los campos
            val nombre = editTextNombre.text.toString()
            val edad = editTextEdad.text.toString()
            val ciudad = editTextCiudad.text.toString()
            val correo = editTextCorreo.text.toString()

            // Validaciones
            if (nombre.isEmpty() || edad.isEmpty() || ciudad.isEmpty() || correo.isEmpty()) {
                // Mostrar un mensaje si algún campo está vacío
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else if (!validarEdad(edad)) {
                // Validar que la edad esté entre 1 y 99
                Toast.makeText(this, "Ingrese una edad válida", Toast.LENGTH_SHORT).show()
            } else if (ciudad.matches(".*\\d.*".toRegex())) {
                // Validación para la ciudad (no permitir números)
                Toast.makeText(this, "Ingrese una ciudad válida", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (!validarCorreo(correo)) {
                // Validar el correo
                Toast.makeText(this, "Correo no válido", Toast.LENGTH_SHORT).show()
            } else {
                // Crear un objeto Usuario con los datos del formulario
                val usuario = Usuario(nombre, edad, ciudad, correo)

                // Pasar el objeto Usuario a ResumenActivity a través del Intent
                val intent = Intent(this, ResumenActivity::class.java)
                intent.putExtra("usuario", usuario) // Pasamos el objeto Usuario
                startActivity(intent)
            }
        }
    }

    // Función para validar la edad (debe estar entre 1 y 99)
    private fun validarEdad(edad: String): Boolean {
        return try {
            val edadInt = edad.toInt()
            edadInt in 1..99
        } catch (e: NumberFormatException) {
            false
        }
    }

    // Función para validar el correo (debe contener '@' y tener un dominio)
    private fun validarCorreo(correo: String): Boolean {
        // Expresión regular para validar el correo
        val regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        return correo.matches(regex.toRegex())
    }

    // Guardar el estado de los campos cuando la pantalla se rota o la actividad se destruye
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("nombre", editTextNombre.text.toString())
        outState.putString("edad", editTextEdad.text.toString())
        outState.putString("ciudad", editTextCiudad.text.toString())
        outState.putString("correo", editTextCorreo.text.toString())
    }

}