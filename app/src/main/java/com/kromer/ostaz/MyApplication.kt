package com.kromer.ostaz

import android.app.Application
import android.content.Intent
import android.os.Build
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.kromer.ostaz.data.local.prefs.Preferences
import com.kromer.ostaz.fcm.MyFirebaseMessagingService
import com.kromer.ostaz.utils.Logger
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application() {

    @Inject
    lateinit var preferences: Preferences

    override fun onCreate() {
        super.onCreate()

        Logger.init()
        startFirebaseNotificationService()
    }

    private fun startFirebaseNotificationService() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }

                preferences.firebaseToken = task.result!!.token
            })
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            startService(Intent(this, MyFirebaseMessagingService::class.java))
        }
    }
}