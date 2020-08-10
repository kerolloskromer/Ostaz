package com.kromer.ostaz.utils

import android.content.Context
import java.io.File
import java.io.IOException

object MediaFileManager {

    fun createFile(context: Context): File? {
        var outputFile: File? = null
        try {
            val outputDir: File = context.cacheDir // context being the Activity pointer
            outputFile = File.createTempFile("audio_", ".mp3", outputDir)
        } catch (e: IOException) {

        }
        return outputFile
    }

    fun deleteFile(filePath: String) {
        val file = File(filePath)
        if (file.exists() && file.isFile && file.canWrite()) {
            Thread(Runnable { file.delete() }).start()
        }
    }
}