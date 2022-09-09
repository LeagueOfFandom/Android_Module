package com.soma.lof.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.soma.common.base.BaseActivity
import com.soma.lof.login.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        binding.logoResId = R.drawable.img_logo
        binding.subLogoResId = R.drawable.img_sub_logo

        CoroutineScope(Dispatchers.Main).launch {
            delay(3000L)
            viewModel.timeOut.value = true
        }

        lifecycleScope.launchWhenCreated {
            viewModel.timeOut.collectLatest { timeOut ->
                if (timeOut) {
                    val intent = Intent(this@SplashActivity, LoginActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    startActivity(intent)
                }
            }
        }
    }

    companion object {
        const val TAG = "SplashActivity"
    }
}