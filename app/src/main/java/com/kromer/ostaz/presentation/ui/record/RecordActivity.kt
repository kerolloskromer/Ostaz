package com.kromer.ostaz.presentation.ui.record

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.kromer.ostaz.databinding.ActivityRecordBinding
import com.kromer.ostaz.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_record.*

@AndroidEntryPoint
class RecordActivity : BaseActivity<ActivityRecordBinding>() {

    private val viewModel: RecordViewModel by viewModels()

    private val REQUEST_VIDEO_CAPTURE = 1

    override fun getVBInflater(): (LayoutInflater) -> ActivityRecordBinding =
        ActivityRecordBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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