package com.soma.lof

import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.soma.lof.core.model.entity.LiveMatchScoreEvent
import com.soma.lof.home.ui.HomeFragment
import org.greenrobot.eventbus.EventBus
import timber.log.Timber
import com.soma.lof.login.ui.SplashActivity

/**
 * Receives Firebase Cloud Messages
 */
class LofFirebaseMessagingService: FirebaseMessagingService() {

    /* 토큰 생성 메서드 */
    override fun onNewToken(token: String) {
        super.onNewToken(token)

        /** [SplashActivity]가 실행될 때마다 fcm 초기화되도록 설정 */
        Timber.d("New firebase token: $token")
    }

    /* 메세지 수신 메서드 */
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Timber.d("Message payload: $message.data")

        message.notification?.let {
            sendNotification(it.title, it.body)
        }

        /**
         * LiveScore is reflected in the [HomeFragment] in real-time
         */
        if (message.data.isNotEmpty()) {
            val homeScore = message.data["homeScore"]
            val awayScore = message.data["awayScore"]

            if (homeScore != null && awayScore != null) {
                EventBus.getDefault().post(LiveMatchScoreEvent(homeScore.toInt(), awayScore.toInt()))
            }
        }
    }

    /* 알림 생성 메서드 */
    private fun sendNotification(
        title: String?,
        message: String?
    ) {

        //알림이 여러개 표시되도록 requestCode 를 추가
        NotificationManagerCompat.from(this)
            .notify(0, createNotification(title, message))
//        NotificationManagerCompat.from(this).notify(0, createNotificatioSummary(title, message))
    }


    private fun createNotification(
        title: String?,
        message: String?): Notification {

        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        val pendingIntent = PendingIntent.getActivity(this,
          0, intent, PendingIntent.FLAG_IMMUTABLE) //알림이 여러개 표시되도록 requestCode 를 추가

        val notificationBuilder = NotificationCompat.Builder(this, LofApplication.channelID)
            .setSmallIcon(R.drawable.lof)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent) //알림 눌렀을 때 실행할 Intent 설정
            .setAutoCancel(true)

        return notificationBuilder.build()
    }

    companion object {
        const val TAG = "LoFFirebaseMessagingService"
    }
}