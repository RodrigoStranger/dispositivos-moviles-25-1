package com.example.ejercicio1

/*
    Autor: Rodrigo Emerson Infanzon Acosta
    Curso: Programacion De Dispositivos Moviles
    Semestre: VI
    Fecha: 30/03/2025
    Ultima modificacion: 30/03/2025 0:50 am
*/

/*
    Agregar una imagen a tu actividad.
    Programar la funcionalidad para que al hacer clic en la imagen, se muestre un Toast con un mensaje personalizado.
*/

// Importaciones necesarias para la funcionalidad de la aplicación
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

// Clase principal de la actividad que hereda de ComponentActivity
class MainActivity : ComponentActivity() {
    // Metodo que se ejecuta cuando se crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Establece el contenido de la actividad usando Compose
        setContent {
            // Aplica el tema de la aplicación
            MaterialTheme {
                // Crea una superficie que ocupa todo el espacio disponible
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF122C4F) // Color azul #122c4f
                ) {
                    // Llama a la función que define la pantalla principal
                    MainScreen()
                }
            }
        }
    }
}

// Función composable que define la interfaz de usuario principal
@Composable
fun MainScreen() {
    // Obtiene el contexto de la aplicación para mostrar el Toast
    val context = LocalContext.current
    
    // Crea un contenedor Box que ocupa todo el espacio disponible
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Crea una imagen que ocupa 200dp x 200dp
        Image(
            painter = painterResource(id = R.drawable.cat),
            contentDescription = "Imagen de un gato",
            modifier = Modifier
                .size(200.dp)
                .clickable {
                    // Obtiene el nombre del archivo de imagen desde los recursos
                    val imageName = context.resources.getResourceEntryName(R.drawable.cat)
                    // Muestra un mensaje Toast cuando se hace clic en la imagen
                    Toast.makeText(
                        context,
                        "¡Has hecho click en la imagen $imageName!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        )
    }
}