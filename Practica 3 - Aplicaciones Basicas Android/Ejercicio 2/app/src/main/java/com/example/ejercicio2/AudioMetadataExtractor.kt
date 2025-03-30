package com.example.ejercicio2

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import java.io.File
import java.io.FileOutputStream

class AudioMetadataExtractor(private val context: Context) {
    private val metadataRetriever = MediaMetadataRetriever()

    fun extractMetadata(audioUri: Uri): AudioMetadata {
        metadataRetriever.setDataSource(context, audioUri)
        
        val title = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE) ?: "Unknown Title"
        val artist = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST) ?: "Unknown Artist"
        val album = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM) ?: "Unknown Album"
        
        // Extraer la imagen del álbum
        val albumArt = metadataRetriever.embeddedPicture
        var albumArtPath: String? = null
        
        if (albumArt != null) {
            // Convertir el byte array a Bitmap
            val bitmap = BitmapFactory.decodeByteArray(albumArt, 0, albumArt.size)
            
            // Guardar la imagen en el almacenamiento interno de la app
            val fileName = "album_art_${System.currentTimeMillis()}.jpg"
            val file = File(context.filesDir, fileName)
            
            FileOutputStream(file).use { out ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            }
            
            albumArtPath = file.absolutePath
        }
        
        return AudioMetadata(
            title = title,
            artist = artist,
            album = album,
            albumArtPath = albumArtPath
        )
    }

    fun release() {
        metadataRetriever.release()
    }
}

data class AudioMetadata(
    val title: String,
    val artist: String,
    val album: String,
    val albumArtPath: String?
) 