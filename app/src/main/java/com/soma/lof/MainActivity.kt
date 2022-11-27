package com.soma.lof

import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.soma.common.ui.base.BaseActivity
import com.soma.common.ui.util.MainActivityUtil
import com.soma.common.ui.util.NavigationFlow
import com.soma.common.ui.util.Navigator
import com.soma.common.ui.util.ToFlowNavigable
import com.soma.lof.databinding.ActivityMainBinding
import com.soma.lof.login.ui.LoginActivity
import com.soma.lof.select_team.ui.SelectTeamActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main), ToFlowNavigable,
    MainActivityUtil {

    private val navigator: Navigator = Navigator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.home_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navigator.navController = navController
        NavigationUI.setupWithNavController(binding.bottomNav, navController)
    }

    override fun navigateToFlow(flow: NavigationFlow) {
        navigator.navigateToFlow(flow)
    }

    /** [MainActivityUtil]
     *  MainActivity가 최상단 Module에 존재하기에 Util은 Common에 놓고 상속받게 함으로써
     *  하위 모듈에서 MainActivity의 권한을 필요로 하는 작업이 가능하게 함.
     */
    override fun startSelectTeamActivity() {
        val intent = Intent(this, SelectTeamActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }

    override fun startLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }
}