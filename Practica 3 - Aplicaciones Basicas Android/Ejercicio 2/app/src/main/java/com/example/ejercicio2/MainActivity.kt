/*
    Autor: Rodrigo Emerson Infanzon Acosta
    Curso: Programacion De Dispositivos Moviles
    Semestre: VI
    Fecha: 29/03/2025
    Ultima modificacion: 29/03/2025 22:00 pm
*/

/*
    Crea una interfaz de usuario con botones para reproducir, pausar y detener la reproducción de música.
    Implementa la lógica para reproducir un archivo de audio almacenado localmente en el dispositivo al presionar el botón de reproducción.
    Nota: Investigar cómo usar la carpeta res/raw y el objeto MediaPlayer (Android/Kotlin)

*/

package com.example.ejercicio2

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.concurrent.TimeUnit

class PlayerActivity : AppCompatActivity() {

    private lateinit var albumImageView: ImageView
    private lateinit var songTitleTextView: TextView
    private lateinit var artistNameTextView: TextView
    private lateinit var albumNameTextView: TextView
    private lateinit var playPauseButton: ImageButton
    private lateinit var previousButton: ImageButton
    private lateinit var nextButton: ImageButton
    private lateinit var seekBar: SeekBar
    private lateinit var currentTimeTextView: TextView
    private lateinit var totalTimeTextView: TextView
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private var isPlaying = false
    private var currentSongIndex = 0
    private var lastPreviousClickTime = 0L
    private val DOUBLE_CLICK_TIME_DELAY = 300L // 300 milisegundos para el doble clic
    private lateinit var metadataExtractor: AudioMetadataExtractor
    private lateinit var songs: Array<Song>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        // Inicializar el extractor de metadatos
        metadataExtractor = AudioMetadataExtractor(this)

        // Verificar y solicitar permisos si es necesario
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                STORAGE_PERMISSION_CODE)
        }

        // Inicializar vistas
        albumImageView = findViewById(R.id.album_image)
        songTitleTextView = findViewById(R.id.song_title)
        artistNameTextView = findViewById(R.id.artist_name)
        albumNameTextView = findViewById(R.id.album_name)
        playPauseButton = findViewById(R.id.play_pause_button)
        previousButton = findViewById(R.id.previous_button)
        nextButton = findViewById(R.id.next_button)
        seekBar = findViewById(R.id.seek_bar)
        currentTimeTextView = findViewById(R.id.current_time)
        totalTimeTextView = findViewById(R.id.total_time)

        // Configurar el handler para actualizar la barra de progreso
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            val currentPosition = mediaPlayer?.currentPosition ?: 0
            val duration = mediaPlayer?.duration ?: 0
            seekBar.progress = currentPosition
            currentTimeTextView.text = formatTime(currentPosition)
            totalTimeTextView.text = formatTimeRemaining(currentPosition, duration)
            handler.postDelayed(runnable, 1000)
        }

        // Configurar la barra de progreso
        seekBar.thumb.alpha = 0 // Ocultar el thumb inicialmente
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer?.seekTo(progress)
                    currentTimeTextView.text = formatTime(progress)
                    totalTimeTextView.text = formatTimeRemaining(progress, mediaPlayer?.duration ?: 0)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                seekBar?.thumb?.alpha = 255 // Mostrar el thumb cuando se toca
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar?.thumb?.alpha = 0 // Ocultar el thumb cuando se suelta
            }
        })

        // Configurar botones
        playPauseButton.setOnClickListener {
            if (isPlaying) {
                pauseMusic()
            } else {
                playMusic()
            }
        }

        previousButton.setOnClickListener {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastPreviousClickTime < DOUBLE_CLICK_TIME_DELAY) {
                // Doble clic detectado - cambiar a la canción anterior
                playPreviousSong()
                lastPreviousClickTime = 0 // Resetear el tiempo
            } else {
                // Primer clic - reiniciar la canción actual
                lastPreviousClickTime = currentTime
                mediaPlayer?.seekTo(0)
                mediaPlayer?.start()
            }
        }

        nextButton.setOnClickListener {
            playNextSong()
        }

        // Cargar las canciones de la carpeta raw
        loadSongsFromRaw()
    }

    private fun loadSongsFromRaw() {
        // Obtener todos los recursos de tipo raw
        val fields = R.raw::class.java.fields
        val songList = mutableListOf<Song>()

        for (field in fields) {
            val resourceId = field.getInt(null)
            val resourceName = field.name
            val audioUri = Uri.parse("android.resource://$packageName/$resourceId")
            
            // Extraer metadatos de la canción
            val metadata = metadataExtractor.extractMetadata(audioUri)
            
            // Crear objeto Song con los metadatos extraídos
            val song = Song(
                title = metadata.title,
                artist = metadata.artist,
                album = metadata.album,
                audioResourceId = resourceId,
                imageResourceId = R.drawable.default_image // Imagen por defecto
            )
            
            songList.add(song)
        }

        // Convertir la lista a array
        songs = songList.toTypedArray()

        // Si hay canciones, cargar la primera
        if (songs.isNotEmpty()) {
            loadSong(currentSongIndex)
        }
    }

    private fun loadSong(index: Int) {
        // Liberar recursos del MediaPlayer anterior
        mediaPlayer?.release()
        mediaPlayer = null

        // Crear nuevo MediaPlayer con la canción seleccionada
        mediaPlayer = MediaPlayer.create(this, songs[index].audioResourceId)
        mediaPlayer?.setOnCompletionListener {
            playNextSong()
        }

        // Extraer metadatos de la canción
        val audioUri = Uri.parse("android.resource://$packageName/${songs[index].audioResourceId}")
        val metadata = metadataExtractor.extractMetadata(audioUri)

        // Actualizar UI con los metadatos extraídos
        songTitleTextView.text = metadata.title
        artistNameTextView.text = metadata.artist
        albumNameTextView.text = metadata.album
        
        // Si hay una imagen de álbum, cargarla
        metadata.albumArtPath?.let { path ->
            albumImageView.setImageBitmap(BitmapFactory.decodeFile(path))
        } ?: run {
            // Si no hay imagen de álbum, usar la imagen por defecto
            albumImageView.setImageResource(songs[index].imageResourceId)
        }

        seekBar.max = mediaPlayer?.duration ?: 0
        seekBar.progress = 0
        currentTimeTextView.text = "0:00"
        totalTimeTextView.text = formatTimeRemaining(0, mediaPlayer?.duration ?: 0)

        // Configurar el botón de play/pause para mostrar el estado inicial
        playPauseButton.setImageResource(R.drawable.ic_play)
        isPlaying = false
    }

    private fun loadAndPlaySong(index: Int) {
        loadSong(index)
        playMusic()
    }

    private fun playMusic() {
        mediaPlayer?.start()
        isPlaying = true
        playPauseButton.setImageResource(R.drawable.ic_pause)
        handler.postDelayed(runnable, 0)
    }

    private fun pauseMusic() {
        mediaPlayer?.pause()
        isPlaying = false
        playPauseButton.setImageResource(R.drawable.ic_play)
        handler.removeCallbacks(runnable)
    }

    private fun playNextSong() {
        currentSongIndex = (currentSongIndex + 1) % songs.size
        loadAndPlaySong(currentSongIndex)
    }

    private fun playPreviousSong() {
        currentSongIndex = if (currentSongIndex > 0) currentSongIndex - 1 else songs.size - 1
        loadAndPlaySong(currentSongIndex)
    }

    private fun formatTime(milliseconds: Int): String {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds.toLong())
        val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds.toLong()) % 60
        return String.format("%d:%02d", minutes, seconds)
    }

    private fun formatTimeRemaining(currentPosition: Int, duration: Int): String {
        val remaining = duration - currentPosition
        val minutes = TimeUnit.MILLISECONDS.toMinutes(remaining.toLong())
        val seconds = TimeUnit.MILLISECONDS.toSeconds(remaining.toLong()) % 60
        return String.format("-%d:%02d", minutes, seconds)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
        handler.removeCallbacks(runnable)
        metadataExtractor.release()
    }

    companion object {
        private const val STORAGE_PERMISSION_CODE = 100
    }
}

data class Song(
    val title: String,
    val artist: String,
    val album: String,
    val audioResourceId: Int,
    val imageResourceId: Int
)
