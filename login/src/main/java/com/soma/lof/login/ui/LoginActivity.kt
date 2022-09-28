package com.soma.lof.login.ui

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.soma.common.base.BaseActivity
import com.soma.common_ui.util.Navigator
import com.soma.lof.login.R
import com.soma.lof.login.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {


    private val navigator: Navigator = Navigator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.login_fragment_view) as NavHostFragment
        val navController = navHostFragment.navController
        navigator.navController = navController
    }

    companion object {
        private const val TAG = "LoginActivity"
    }
}