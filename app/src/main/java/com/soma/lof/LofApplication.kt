package com.soma.lof

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.util.*

/*
* 대한민국 - country: KR, language: ko
* 미국 - country: US, language: en
* 영국 - country: GB, language: en
*/
@HiltAndroidApp
class LofApplication : Application() {

    companion object {
        const val channelID = "LoF_Match_Channel"
        const val channelName = "LoF_Notification"
        const val channelDescription = "Push_Notification_Channel"
        var channel: NotificationChannel? = null
    }

    private lateinit var language: String
    private lateinit var country: String

    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val primaryLocale: Locale = applicationContext.resources.configuration.locales.get(0)
            language = primaryLocale.language
            country = primaryLocale.country
        } else {
            language = Locale.getDefault().language
            country = Locale.getDefault().country
        }
    }

    fun getApplicationLanguage() = language
    fun getApplicationCountry() = country

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