package com.example.editordeperfilconconfirmacion

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.editordeperfilconconfirmacion.model.Usuario

class ResumenActivity : AppCompatActivity() {

    // Metodo onCreate que se ejecuta cuando la actividad es creada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resumen_activity) // Establecemos el layout de la actividad

        // Obtener el objeto Usuario enviado a través del Intent
        val usuario: Usuario? = intent.getParcelableExtra("usuario")

        // Si el objeto Usuario es null, mostramos un mensaje
        if (usuario != null) {
            // Referencias a los TextView para mostrar los datos
            val textViewNombre: TextView = findViewById(R.id.textViewNombre)
            val textViewEdad: TextView = findViewById(R.id.textViewEdad)
            val textViewCiudad: TextView = findViewById(R.id.textViewCiudad)
            val textViewCorreo: TextView = findViewById(R.id.textViewCorreo)

            // Mostrar los datos en los TextView utilizando getString con placeholders
            textViewNombre.text = getString(R.string.texto_nombre, usuario.nombre)
            textViewEdad.text = getString(R.string.texto_edad, usuario.edad)
            textViewCiudad.text = getString(R.string.texto_ciudad, usuario.ciudad)
            textViewCorreo.text = getString(R.string.texto_correo, usuario.correo)
        }

        // Botón "Confirmar"
        val btnConfirmar: Button = findViewById(R.id.btnConfirmar)
        btnConfirmar.setOnClickListener {
            // Volver a la primera pantalla (FormActivity) y mostrar el Toast
            val intent = Intent(this, FormularioActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, getString(R.string.perfil_guardado), Toast.LENGTH_SHORT).show()
        }

        // Botón "Volver a editar" para regresar a la pantalla anterior
        val btnVolverEditar: Button = findViewById(R.id.btnVolverEditar)
        btnVolverEditar.setOnClickListener {
            // Volver a la pantalla anterior (FormActivity)
            finish()
        }
    }
}