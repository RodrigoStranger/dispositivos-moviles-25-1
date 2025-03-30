// Clase utilitaria para extraer metadatos de archivos de audio
// Utiliza MediaMetadataRetriever para obtener información como:
// - Título de la canción
// - Nombre del artista
// - Nombre del álbum
// - Imagen del álbum (si está disponible)

/*
    Autor: Rodrigo Emerson Infanzon Acosta
    Curso: Programacion De Dispositivos Moviles
    Semestre: VI
    Fecha: 29/03/2025
    Ultima modificacion: 29/03/2025 23:20 pm
*/

package com.example.ejercicio2

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import java.io.File
import java.io.FileOutputStream

// Clase principal para la extracción de metadatos de audio
// Requiere un contexto para acceder a los recursos y almacenamiento
class AudioMetadataExtractor(private val context: Context) {
    // Instancia del extractor de metadatos de Android
    private val metadataRetriever = MediaMetadataRetriever()

    // Extrae los metadatos de un archivo de audio dado su URI
    // Retorna un objeto AudioMetadata con toda la información extraída
    fun extractMetadata(audioUri: Uri): AudioMetadata {
        // Configura la fuente de datos para el extractor
        metadataRetriever.setDataSource(context, audioUri)
        
        // Extrae la información básica del audio
        // Si algún dato no está disponible, usa "Unknown" como valor por defecto
        val title = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE) ?: "Unknown Title"
        val artist = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST) ?: "Unknown Artist"
        val album = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM) ?: "Unknown Album"
        
        // Intenta extraer la imagen del álbum si está disponible
        val albumArt = metadataRetriever.embeddedPicture
        var albumArtPath: String? = null
        
        // Si existe una imagen del álbum, la procesa y guarda
        if (albumArt != null) {
            // Convierte el array de bytes a un objeto Bitmap
            val bitmap = BitmapFactory.decodeByteArray(albumArt, 0, albumArt.size)
            
            // Genera un nombre único para el archivo de imagen
            val fileName = "album_art_${System.currentTimeMillis()}.jpg"
            val file = File(context.filesDir, fileName)
            
            // Guarda la imagen en el almacenamiento interno de la app
            // Usa compresión JPEG con máxima calidad
            FileOutputStream(file).use { out ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            }
            
            // Guarda la ruta del archivo para uso posterior
            albumArtPath = file.absolutePath
        }
        
        // Crea y retorna un objeto con todos los metadatos extraídos
        return AudioMetadata(
            title = title,
            artist = artist,
            album = album,
            albumArtPath = albumArtPath
        )
    }

    // Libera los recursos utilizados por el extractor
    // Debe llamarse cuando ya no se necesite extraer más metadatos
    fun release() {
        metadataRetriever.release()
    }
}

// Clase de datos que representa los metadatos de un archivo de audio
data class AudioMetadata(
    val title: String,     // Título de la canción
    val artist: String,    // Nombre del artista
    val album: String,     // Nombre del álbum
    val albumArtPath: String?  // Ruta al archivo de imagen del álbum (null si no hay imagen)
) 
