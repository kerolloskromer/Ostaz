package com.kromer.ostaz.presentation.ui.main.dashboard

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.kromer.ostaz.databinding.FragmentDashboardBinding
import com.kromer.ostaz.presentation.base.BaseFragment
import com.kromer.ostaz.utils.MediaPlayerManager
import com.kromer.ostaz.utils.MediaRecorderManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {

    private val RECORD_AUDIO_PERMISSION_REQUEST_CODE = 123
    private val dashboardViewModel: DashboardViewModel by viewModels()

    var started: Boolean = false

    override fun getVBInflater(): (LayoutInflater) -> FragmentDashboardBinding =
        FragmentDashboardBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRecord.text = "Start"

        binding.btnRecord.setOnClickListener {
            if (isPermissionGranted()) {
                doWork()
            } else {
                requestPermission()
            }
        }

    }

    private fun doWork() {
        if (started) {
            binding.btnRecord.text = "Start"
            MediaRecorderManager.stop()
            dashboardViewModel.uploadPost(
                MediaRecorderManager.file?.path!!,
                MediaRecorderManager.file!!
            )
            // TODO remove this
            // we play the file for testing
            MediaPlayerManager.start(MediaRecorderManager.file?.path!!)
            started = false
        } else {
            binding.btnRecord.text = "Stop"
            MediaPlayerManager.stop()
            MediaRecorderManager.start(requireContext())
            started = true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            RECORD_AUDIO_PERMISSION_REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay!
                    doWork()
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }

    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.RECORD_AUDIO),
            RECORD_AUDIO_PERMISSION_REQUEST_CODE
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        MediaPlayerManager.stop()
        MediaRecorderManager.stop()
    }
}
