package com.kromer.ostaz.utils

import android.media.AudioAttributes
import android.media.MediaPlayer

object MediaPlayerManager {

    private var mediaPlayer: MediaPlayer? = null
    private var playbackPosition: Int = 0

    fun start(url: String) {
        mediaPlayer = MediaPlayer().apply {
            setOnPreparedListener {
                it.start()
            }
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
            setAudioAttributes(audioAttributes)
            setDataSource(url)
            prepareAsync()
        }
    }

    fun stop() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    fun toggle() {
        if (mediaPlayer?.isPlaying!!) pause() else resume()
    }

    private fun resume() {
        mediaPlayer?.seekTo(playbackPosition)
        mediaPlayer?.start()
    }

    private fun pause() {
        playbackPosition = mediaPlayer?.currentPosition!!
        mediaPlayer?.pause()
    }
}