package com.octaneocatane.alarmapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            showNotification(it)
            showActivity(it)
        }
    }

    private fun showActivity(context: Context) {
        val intent = Intent(context, AlarmActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 11, intent, 0)

    }

    private fun showNotification(it: Context) {
        val intent = Intent(it, AlarmActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(it, 0, intent, 0)

        val notificationManager = getSystemService(
            it,
            NotificationManager::class.java
        ) as NotificationManager

        createNotificationChannel(notificationManager)

        val notification = NotificationCompat.Builder(it, CHANNEL_ID)
            .setContentTitle("Title")
            .setContentText("Text")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    companion object {

        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"
        private const val NOTIFICATION_ID = 1

        fun newIntent(context: Context): Intent {
            return Intent(context, AlarmReceiver::class.java)
        }
    }
}