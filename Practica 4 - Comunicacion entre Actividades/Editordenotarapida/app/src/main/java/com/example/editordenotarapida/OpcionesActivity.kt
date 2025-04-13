package com.example.editordenotarapida

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class OpcionesActivity : AppCompatActivity() {

    private lateinit var textViewNota: TextView
    private lateinit var btnCompartirCorreo: Button
    private lateinit var btnEditar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.opciones_activity)

        // Inicializamos las vistas
        textViewNota = findViewById(R.id.textViewNota)
        btnCompartirCorreo = findViewById(R.id.buttonCompartirCorreo)
        btnEditar = findViewById(R.id.buttonEditar)

        // Recibimos la nota desde la actividad anterior
        val nota = intent.getStringExtra("nota")
        textViewNota.text = nota  // Mostramos la nota en el TextView

        // Acción para compartir por correo (simplemente muestra un Toast)
        btnCompartirCorreo.setOnClickListener {
            Toast.makeText(this, "Compartido por correo", Toast.LENGTH_SHORT).show()
        }

        // Acción para regresar a la actividad anterior (EditorActivity)
        btnEditar.setOnClickListener {
            val intent = Intent()
            intent.putExtra("nota", nota)  // Pasamos la nota de vuelta
            setResult(RESULT_OK, intent)  // Indicamos que el resultado es correcto
            finish()  // Cerramos la actividad y regresamos a la anterior
        }
    }
}