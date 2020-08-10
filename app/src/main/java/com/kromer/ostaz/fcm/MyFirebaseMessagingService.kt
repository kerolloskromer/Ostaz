package com.kromer.ostaz.fcm

import android.app.Notification
import android.graphics.Bitmap
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.kromer.ostaz.data.local.prefs.Preferences
import com.kromer.ostaz.utils.Logger
import com.kromer.ostaz.utils.NotificationUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyFirebaseMessagingService : FirebaseMessagingService() {

    @Inject
    lateinit var preferences: Preferences

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        preferences.firebaseToken = token
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        if (remoteMessage.data.isNotEmpty()) {
            // in case we send notification from console with data {"key":"value"}
            // in case we send notification from backend {your_custom_object}
            Logger.d("Data payload: ", remoteMessage.data.toString())

            val title: String? = remoteMessage.data["title"]
            val body: String? = remoteMessage.data["body"]
            sendNotification(title, body)

        } else if (remoteMessage.notification != null) {
            // in case we send notification from console without data
            Logger.d("Notification payload: ", remoteMessage.notification.toString())
            val title = remoteMessage.notification!!.title
            val body = remoteMessage.notification!!.body
            //show notification
            sendNotification(title, body)
        }
    }

    private fun sendNotification(title: String?, body: String?) {
        val notificationId = System.currentTimeMillis().toInt()
        val largeIcon: Bitmap? = null
        val notification: Notification =
            NotificationUtils.buildNotification(this, largeIcon, title, body, null)
        NotificationUtils.showNotification(this, notification, notificationId)
    }
}