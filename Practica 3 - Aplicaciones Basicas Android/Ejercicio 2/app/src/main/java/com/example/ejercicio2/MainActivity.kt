/*
    Autor: Rodrigo Emerson Infanzon Acosta
    Curso: Programacion De Dispositivos Moviles
    Semestre: VI
    Fecha: 14/03/2025
    Ultima modificacion: 29/03/2025 23:20 pm
*/

/*
    Esta aplicación implementa un reproductor de música con las siguientes características:
    - Reproducción de archivos de audio locales desde la carpeta raw
    - Extracción automática de metadatos (título, artista, álbum, imagen)
    - Interfaz de usuario moderna y responsive con tema oscuro
    - Controles completos de reproducción (play, pause, next, previous)
    - Barra de progreso interactiva con tiempo transcurrido y restante
    - Soporte para doble clic en botón anterior para reiniciar canción

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

/**
 * Actividad principal del reproductor de música.
 * Maneja la interfaz de usuario y la lógica de reproducción.
 */
class PlayerActivity : AppCompatActivity() {

    // Referencias a elementos de la UI
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

    // Variables para la reproducción de música
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private var isPlaying = false
    private var currentSongIndex = 0

    // Variables para el control de doble clic
    private var lastPreviousClickTime = 0L
    private val DOUBLE_CLICK_TIME_DELAY = 300L // Tiempo máximo entre clics para detectar doble clic

    // Herramientas para metadatos y gestión de canciones
    private lateinit var metadataExtractor: AudioMetadataExtractor
    private lateinit var songs: Array<Song>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        // Inicializar el extractor de metadatos
        metadataExtractor = AudioMetadataExtractor(this)

        // Verificar y solicitar permisos de almacenamiento
        checkAndRequestPermissions()

        // Inicializar todas las vistas
        initializeViews()

        // Configurar el handler para actualizar la barra de progreso
        setupProgressHandler()

        // Configurar la barra de progreso
        setupSeekBar()

        // Configurar los botones de control
        setupControlButtons()

        // Cargar las canciones de la carpeta raw
        loadSongsFromRaw()
    }

    /**
     * Verifica y solicita los permisos necesarios para acceder al almacenamiento
     */
    private fun checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                STORAGE_PERMISSION_CODE)
        }
    }

    /**
     * Inicializa todas las referencias a las vistas del layout
     */
    private fun initializeViews() {
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
    }

    /**
     * Configura el handler para actualizar la barra de progreso
     * Se ejecuta cada segundo para actualizar el tiempo y la posición
     */
    private fun setupProgressHandler() {
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            val currentPosition = mediaPlayer?.currentPosition ?: 0
            val duration = mediaPlayer?.duration ?: 0
            seekBar.progress = currentPosition
            currentTimeTextView.text = formatTime(currentPosition)
            totalTimeTextView.text = formatTimeRemaining(currentPosition, duration)
            handler.postDelayed(runnable, 1000)
        }
    }

    /**
     * Configura la barra de progreso y sus listeners
     */
    private fun setupSeekBar() {
        seekBar.thumb.alpha = 0 // Ocultar el thumb inicialmente
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer?.seekTo(progress)
                    updateTimeDisplays(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                seekBar?.thumb?.alpha = 255 // Mostrar el thumb al tocar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar?.thumb?.alpha = 0 // Ocultar el thumb al soltar
            }
        })
    }

    /**
     * Configura los botones de control y sus listeners
     */
    private fun setupControlButtons() {
        playPauseButton.setOnClickListener {
            togglePlayPause()
        }

        previousButton.setOnClickListener {
            handlePreviousButton()
        }

        nextButton.setOnClickListener {
            playNextSong()
        }
    }

    /**
     * Maneja la lógica del botón anterior
     * Detecta doble clic para cambiar de canción o reiniciar la actual
     */
    private fun handlePreviousButton() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastPreviousClickTime < DOUBLE_CLICK_TIME_DELAY) {
            playPreviousSong()
            lastPreviousClickTime = 0
        } else {
            lastPreviousClickTime = currentTime
            restartCurrentSong()
        }
    }

    /**
     * Carga todas las canciones de la carpeta raw
     * Extrae los metadatos de cada canción
     */
    private fun loadSongsFromRaw() {
        val fields = R.raw::class.java.fields
        val songList = mutableListOf<Song>()

        for (field in fields) {
            val resourceId = field.getInt(null)
            val audioUri = Uri.parse("android.resource://$packageName/$resourceId")
            val metadata = metadataExtractor.extractMetadata(audioUri)
            
            songList.add(Song(
                title = metadata.title,
                artist = metadata.artist,
                album = metadata.album,
                audioResourceId = resourceId,
                imageResourceId = R.drawable.default_image
            ))
        }

        songs = songList.toTypedArray()
        if (songs.isNotEmpty()) {
            loadSong(currentSongIndex)
        }
    }

    /**
     * Carga una canción específica y actualiza la UI
     * @param index Índice de la canción a cargar
     */
    private fun loadSong(index: Int) {
        // Liberar recursos del MediaPlayer anterior
        releaseMediaPlayer()

        // Crear nuevo MediaPlayer
        mediaPlayer = MediaPlayer.create(this, songs[index].audioResourceId)
        mediaPlayer?.setOnCompletionListener {
            playNextSong()
        }

        // Extraer y mostrar metadatos
        updateSongMetadata(index)

        // Configurar la UI inicial
        setupInitialUI()
    }

    /**
     * Actualiza los metadatos mostrados en la UI
     * @param index Índice de la canción actual
     */
    private fun updateSongMetadata(index: Int) {
        val audioUri = Uri.parse("android.resource://$packageName/${songs[index].audioResourceId}")
        val metadata = metadataExtractor.extractMetadata(audioUri)

        songTitleTextView.text = metadata.title
        artistNameTextView.text = metadata.artist
        albumNameTextView.text = metadata.album
        
        metadata.albumArtPath?.let { path ->
            albumImageView.setImageBitmap(BitmapFactory.decodeFile(path))
        } ?: run {
            albumImageView.setImageResource(songs[index].imageResourceId)
        }
    }

    /**
     * Configura el estado inicial de la UI
     */
    private fun setupInitialUI() {
        seekBar.max = mediaPlayer?.duration ?: 0
        seekBar.progress = 0
        currentTimeTextView.text = "0:00"
        totalTimeTextView.text = formatTimeRemaining(0, mediaPlayer?.duration ?: 0)
        playPauseButton.setImageResource(R.drawable.ic_play)
        isPlaying = false
    }

    /**
     * Alterna entre reproducir y pausar
     */
    private fun togglePlayPause() {
        if (isPlaying) {
            pauseMusic()
        } else {
            playMusic()
        }
    }

    /**
     * Inicia la reproducción de música
     */
    private fun playMusic() {
        mediaPlayer?.start()
        isPlaying = true
        playPauseButton.setImageResource(R.drawable.ic_pause)
        handler.postDelayed(runnable, 0)
    }

    /**
     * Pausa la reproducción de música
     */
    private fun pauseMusic() {
        mediaPlayer?.pause()
        isPlaying = false
        playPauseButton.setImageResource(R.drawable.ic_play)
        handler.removeCallbacks(runnable)
    }

    /**
     * Reproduce la siguiente canción
     */
    private fun playNextSong() {
        currentSongIndex = (currentSongIndex + 1) % songs.size
        loadAndPlaySong(currentSongIndex)
    }

    /**
     * Reproduce la canción anterior
     */
    private fun playPreviousSong() {
        currentSongIndex = if (currentSongIndex > 0) currentSongIndex - 1 else songs.size - 1
        loadAndPlaySong(currentSongIndex)
    }

    /**
     * Reinicia la canción actual
     */
    private fun restartCurrentSong() {
        mediaPlayer?.seekTo(0)
        mediaPlayer?.start()
    }

    /**
     * Carga y reproduce una canción específica
     * @param index Índice de la canción a reproducir
     */
    private fun loadAndPlaySong(index: Int) {
        loadSong(index)
        playMusic()
    }

    /**
     * Formatea el tiempo en milisegundos a formato mm:ss
     * @param milliseconds Tiempo en milisegundos
     * @return Tiempo formateado
     */
    private fun formatTime(milliseconds: Int): String {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds.toLong())
        val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds.toLong()) % 60
        return String.format("%d:%02d", minutes, seconds)
    }

    /**
     * Formatea el tiempo restante en formato -mm:ss
     * @param currentPosition Posición actual en milisegundos
     * @param duration Duración total en milisegundos
     * @return Tiempo restante formateado
     */
    private fun formatTimeRemaining(currentPosition: Int, duration: Int): String {
        val remaining = duration - currentPosition
        val minutes = TimeUnit.MILLISECONDS.toMinutes(remaining.toLong())
        val seconds = TimeUnit.MILLISECONDS.toSeconds(remaining.toLong()) % 60
        return String.format("-%d:%02d", minutes, seconds)
    }

    /**
     * Actualiza las visualizaciones de tiempo
     * @param progress Progreso actual en milisegundos
     */
    private fun updateTimeDisplays(progress: Int) {
        currentTimeTextView.text = formatTime(progress)
        totalTimeTextView.text = formatTimeRemaining(progress, mediaPlayer?.duration ?: 0)
    }

    /**
     * Libera los recursos del MediaPlayer
     */
    private fun releaseMediaPlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    /**
     * Limpia los recursos cuando se destruye la actividad
     */
    override fun onDestroy() {
        super.onDestroy()
        releaseMediaPlayer()
        handler.removeCallbacks(runnable)
        metadataExtractor.release()
    }

    companion object {
        private const val STORAGE_PERMISSION_CODE = 100
    }
}

/**
 * Data class que representa una canción
 * @property title Título de la canción
 * @property artist Nombre del artista
 * @property album Nombre del álbum
 * @property audioResourceId ID del recurso de audio
 * @property imageResourceId ID del recurso de imagen
 */
data class Song(
    val title: String,
    val artist: String,
    val album: String,
    val audioResourceId: Int,
    val imageResourceId: Int
)
