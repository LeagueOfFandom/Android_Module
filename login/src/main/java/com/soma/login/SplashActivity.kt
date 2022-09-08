package com.soma.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.soma.login.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        supportActionBar?.hide()
        binding.logoResId = R.drawable.img_logo
        binding.subLogoResId = R.drawable.img_sub_logo

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registeration token failed", task.exception)
                return@OnCompleteListener

                /*
                * TODO 만약 실패할 경우 백그라운드에서 비동기로 성공할 때 까지 돌려야하는가?
                */
            }

            val token = task.result
            Log.d(TAG, "token: $token")
            Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
        })
    }

    companion object {
        const val TAG = "SplashActivity"
    }
}