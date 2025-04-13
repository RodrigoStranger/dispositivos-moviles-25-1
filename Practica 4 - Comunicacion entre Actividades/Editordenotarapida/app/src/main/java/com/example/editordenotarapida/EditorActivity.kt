package com.example.editordenotarapida

/*
    Autor: Rodrigo Emerson Infanzon Acosta
    Curso: Programacion De Dispositivos Moviles
    Semestre: VI
    Fecha: 13/04/2025
    Ultima modificacion: 13/04/2025 17:13pm
*/

/*
     Permitir al usuario escribir una nota,
     enviarla a otra actividad para elegir compartirla o volver a editar.

     Actividad 1 – EditorActivity
     - Un campo de texto (EditText) para escribir una nota.
     - Un botón que diga “Compartir”.
     - Al presionar, la nota se manda a otra pantalla.


     Actividad 2 – OpcionesActivity
     - Muestra la nota recibida.
     Tiene dos botones:
     - “Compartir por correo” (muestra un Toast que diga “Compartido por correo”).
     - “Editar de nuevo” (vuelve a la pantalla anterior con el texto para seguir escribiendo).
*/

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditorActivity : AppCompatActivity() {

    private lateinit var editTextNota: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editor_activity)

        // Inicializamos las vistas
        editTextNota = findViewById(R.id.editTextNota)
        val btnCompartir: Button = findViewById(R.id.buttonCompartir)

        // Si la actividad se reinicia (por ejemplo, por rotación), restauramos el texto guardado
        if (savedInstanceState != null) {
            val savedNota = savedInstanceState.getString(R.string.key_nota.toString())
            editTextNota.setText(savedNota)
        }

        // Acción para compartir la nota
        btnCompartir.setOnClickListener {
            val nota = editTextNota.text.toString()

            // Verificamos si la nota está vacía
            if (nota.isEmpty()) {
                Toast.makeText(this, R.string.error_nota_vacia, Toast.LENGTH_SHORT).show()
            } else {
                // Enviar la nota a OpcionesActivity
                val intent = Intent(this, OpcionesActivity::class.java)
                intent.putExtra(R.string.key_nota.toString(), nota)  // Pasamos la nota

                // Llamamos a la actividad con la solicitud de resultado
                startActivityForResult(intent, 1)
            }
        }
    }

    // Guardamos el contenido de la nota en el estado de la actividad
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("nota", editTextNota.text.toString())  // Guardamos el texto de la nota
    }

    // Recibimos el resultado de OpcionesActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK) {
            val nota = data?.getStringExtra(R.string.key_nota.toString())
            val compartido = data?.getBooleanExtra( R.string.key_compartido.toString(), false)

            editTextNota.setText(nota)  // Actualizamos el EditText con la nota recibida

            // Si la nota fue compartida, limpiamos el campo para ingresar una nueva
            if (compartido == true) {
                editTextNota.text.clear()  // Limpiar el campo
            }
        }
    }
}