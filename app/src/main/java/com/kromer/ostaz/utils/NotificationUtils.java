package com.kromer.ostaz.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.kromer.ostaz.R;


public class NotificationUtils {

    private static final String CHANNEL_ID = "Ostaz";

    public static Notification buildNotification(Context context, Bitmap largeIcon, String title, String body, PendingIntent pendingIntent) {

        if (largeIcon == null) {
            largeIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_home_black_24dp);
        }

        NotificationCompat.Builder noBuilder = new NotificationCompat.Builder(context, CHANNEL_ID);
        noBuilder.setSmallIcon(R.drawable.ic_home_black_24dp) // set small icon with transparent background
                .setColor(context.getResources().getColor(R.color.colorPrimary))
                .setLargeIcon(largeIcon)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(body))
                .setVibrate(getVibrationPattern())
                .setSound(getDefaultRingTone())
                .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                .setNumber(5);

        return noBuilder.build();
    }

    public static void showNotification(Context context, Notification notification, int notificationId) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        AudioAttributes.Builder audioAttributesBuilder = new AudioAttributes.Builder();
        audioAttributesBuilder.setUsage(AudioAttributes.USAGE_NOTIFICATION);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, context.getString(R.string.app_name), NotificationManager.IMPORTANCE_HIGH);
            mChannel.setDescription("Notification from Voicee");
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(getVibrationPattern());
            mChannel.setSound(getDefaultRingTone(), audioAttributesBuilder.build());
            mChannel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(mChannel);
            }
        }

        if (notificationManager != null) {
            notificationManager.notify(notificationId, notification);
        }
    }

    private static Uri getDefaultRingTone() {
        return RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    }

    private static long[] getVibrationPattern() {
        return new long[]{500, 500, 500, 500};
    }
}