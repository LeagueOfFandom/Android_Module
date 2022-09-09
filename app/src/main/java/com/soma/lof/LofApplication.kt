package com.soma.lof

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LofApplication : Application() {

    companion object {
        const val channelID = "LoF_Match_Channel"
        const val channelName = "LoF_Notification"
        const val channelDescription = "Push_Notification_Channel"
        var channel: NotificationChannel? = null
    }

    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //Oreo(26) 이상 버전에는 channel 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = NotificationChannel(
                channelID,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )
            channel!!.description = channelDescription
            notificationManager.createNotificationChannel(channel!!)
        }
    }
}