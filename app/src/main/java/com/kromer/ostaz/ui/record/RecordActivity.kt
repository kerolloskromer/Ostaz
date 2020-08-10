package com.kromer.ostaz.ui.record

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.lifecycle.ViewModelProvider
import com.kromer.ostaz.R
import com.kromer.ostaz.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_record.*

@AndroidEntryPoint
class RecordActivity : BaseActivity() {

    private lateinit var viewModel: RecordViewModel

    private val REQUEST_VIDEO_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        viewModel = ViewModelProvider(this).get(RecordViewModel::class.java)

        dispatchTakeVideoIntent()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            val videoUri: Uri = intent?.data!!
            videoView.setVideoURI(videoUri)
            videoView.start()
        }
    }

    private fun dispatchTakeVideoIntent() {
        Intent(MediaStore.ACTION_VIDEO_CAPTURE).also { takeVideoIntent ->
            takeVideoIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
            }
        }
    }

}