package com.example.editordeperfilconconfirmacion

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
        setContentView(R.layout.form_activity)  // Usa el XML para el diseño

        // Obtener las referencias a los campos y el botón
        editTextNombre = findViewById(R.id.editTextNombre)
        editTextEdad = findViewById(R.id.editTextEdad)
        editTextCiudad = findViewById(R.id.editTextCiudad)
        editTextCorreo = findViewById(R.id.editTextCorreo)
        val btnContinuar: Button = findViewById(R.id.btnContinuar)

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
                Toast.makeText(this, "La edad debe estar entre 1 y 99", Toast.LENGTH_SHORT).show()
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
}
