package com.soma.lof.login.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.soma.common.ui.base.BaseActivity
import com.soma.lof.login.R
import com.soma.lof.login.databinding.ActivitySplashBinding
import com.soma.lof.login.util.LoginUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    @Inject
    @Named("Main")
    lateinit var mainActivityClass: Class<*>

    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        bind {
            logoResId = R.drawable.img_logo
            subLogoResId = R.drawable.img_sub_logo
        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(3000L)
            viewModel.timeOut.value = true
        }

        lifecycleScope.launchWhenCreated {
            viewModel.timeOut.collectLatest { timeOut ->
                if (timeOut) {
                    if (viewModel.autoSignIn.value) {
                        LoginUtil.startMainActivity(this@SplashActivity, mainActivityClass)
                    } else {
                        LoginUtil.startLoginActivity(this@SplashActivity, LoginActivity::class.java)
                    }
                }
            }
        }
    }

    companion object {
        const val TAG = "SplashActivity"
    }
}