package com.soma.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.soma.login.databinding.ActivitySplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        supportActionBar?.hide()
        binding.logoResId = R.drawable.img_logo
        binding.subLogoResId = R.drawable.img_sub_logo
    }

    override fun onResume() {
        super.onResume()

        CoroutineScope(Dispatchers.Main).launch {
            delay(3000L)
            val intent = Intent(this@SplashActivity, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
        }
    }
}