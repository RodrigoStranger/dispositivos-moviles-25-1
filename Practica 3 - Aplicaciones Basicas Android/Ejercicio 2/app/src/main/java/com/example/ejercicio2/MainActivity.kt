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

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.TimeUnit

class PlayerActivity : AppCompatActivity() {

    private lateinit var albumImageView: ImageView
    private lateinit var audioNameTextView: TextView
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

    private val songs = arrayOf(
        Song("Faded", R.raw.alan_walker_faded, R.drawable.audio_image_1),
        Song("Blinding Lights", R.raw.the_weeknd_blinding_lights, R.drawable.audio_image_2),
        Song("Save Your Tears", R.raw.the_weeknd_save_your_tears, R.drawable.audio_image_3),
        Song("Con Calma", R.raw.daddyyankee_con_calma, R.drawable.audio_image_4),
        Song("Shape of You", R.raw.ed_sheeran_shape_of_you, R.drawable.audio_image_5)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        // Inicializar vistas
        albumImageView = findViewById(R.id.album_image)
        audioNameTextView = findViewById(R.id.audio_name)
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

        // Iniciar con la primera canción en pausa
        loadSong(currentSongIndex)
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

        // Actualizar UI
        albumImageView.setImageResource(songs[index].imageResourceId)
        audioNameTextView.text = songs[index].title
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
    }
}

data class Song(
    val title: String,
    val audioResourceId: Int,
    val imageResourceId: Int
)
