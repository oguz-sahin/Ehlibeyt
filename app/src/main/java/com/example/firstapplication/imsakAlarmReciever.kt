package com.example.firstapplication

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi

class imsakAlarmReciever : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {

        val notificationManager: NotificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            "com.example.firstapplication",
            "Men",
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(100, 100, 100, 100)


        val notification = Notification.Builder(context, "com.example.firstapplication")
            .setSmallIcon(R.drawable.adl)
            .setContentTitle("İmsak vakti")
            .setContentText("Akşam namazını kılalım")
            .setAutoCancel(true)


        notificationManager.notify(12345, notification.build())


    }
}