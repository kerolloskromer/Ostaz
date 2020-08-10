package com.kromer.ostaz.utils

import android.content.Context
import android.media.MediaRecorder
import java.io.File
import java.io.IOException

object MediaRecorderManager {

    private var mediaRecorder: MediaRecorder? = null
    var file: File? = null

    fun start(context: Context) {
        file = MediaFileManager.createFile(context)
        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setOutputFile(file?.path)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            try {
                prepare()
            } catch (e: IOException) {
                Logger.e(javaClass.name + "_start", e.message)
            }
            start()
        }
    }

    fun stop() {
        mediaRecorder?.apply {
            stop()
            release()
        }
        mediaRecorder = null
    }
}