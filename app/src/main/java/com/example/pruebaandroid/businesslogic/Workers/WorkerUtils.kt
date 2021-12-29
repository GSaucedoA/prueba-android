package com.example.pruebaandroid.businesslogic.Workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.WorkerThread
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.pruebaandroid.R
import com.example.pruebaandroid.businesslogic.utils.CHANNEL_ID
import com.example.pruebaandroid.businesslogic.utils.NOTIFICATION_ID
import com.example.pruebaandroid.businesslogic.utils.VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
import com.example.pruebaandroid.businesslogic.utils.VERBOSE_NOTIFICATION_CHANNEL_NAME

private const val TAG = "WorkerUtils"

fun makeStatusNotification(message: String, context: Context) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        val name = VERBOSE_NOTIFICATION_CHANNEL_NAME
        val description = VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance)
        channel.description = description

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

        notificationManager?.createNotificationChannel(channel)
    }

    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle(message)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setVibrate(LongArray(0))

    NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())
}

@WorkerThread
class WorkerUtils {
}
