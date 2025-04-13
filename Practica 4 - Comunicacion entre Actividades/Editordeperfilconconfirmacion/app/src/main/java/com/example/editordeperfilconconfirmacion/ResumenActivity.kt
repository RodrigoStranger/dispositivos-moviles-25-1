package com.example.editordeperfilconconfirmacion

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.editordeperfilconconfirmacion.model.Usuario

class ResumenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resumen_activity)

        // Recuperamos el objeto Usuario desde el Intent
        val usuario = intent.getParcelableExtra<Usuario>("usuario")

        // Si el objeto Usuario no es nulo, mostramos los datos
        if (usuario != null) {
            val textViewNombre: TextView = findViewById(R.id.textViewNombre)
            val textViewEdad: TextView = findViewById(R.id.textViewEdad)
            val textViewCiudad: TextView = findViewById(R.id.textViewCiudad)
            val textViewCorreo: TextView = findViewById(R.id.textViewCorreo)

            // Rellenamos los TextViews con los datos del Usuario
            textViewNombre.text = getString(R.string.texto_nombre, usuario.nombre)
            textViewEdad.text = getString(R.string.texto_edad, usuario.edad)
            textViewCiudad.text = getString(R.string.texto_ciudad, usuario.ciudad)
            textViewCorreo.text = getString(R.string.texto_correo, usuario.correo)

            // Botón para confirmar los datos
            val btnConfirmar: Button = findViewById(R.id.btnConfirmar)
            btnConfirmar.setOnClickListener {
                // Crear el Intent para devolver los datos a FormularioActivity
                val resultIntent = Intent()
                resultIntent.putExtra("usuario", usuario)

                // Establecer el resultado y finalizar la actividad
                setResult(RESULT_OK, resultIntent)
                finish() // Finalizamos ResumenActivity y regresamos a FormularioActivity
            }

            // Botón "Volver a editar" para regresar sin guardar
            val btnVolverEditar: Button = findViewById(R.id.btnVolverEditar)
            btnVolverEditar.setOnClickListener {
                finish() // Solo terminamos esta actividad sin devolver datos
            }
        }
    }
}