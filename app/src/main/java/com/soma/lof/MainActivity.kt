package com.soma.lof

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.soma.common.ui.util.NavigationFlow
import com.soma.common.ui.util.Navigator
import com.soma.common.ui.util.ToFlowNavigable
import com.soma.lof.databinding.ActivityMainBinding
import com.soma.common.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main),
    ToFlowNavigable {

    private val navigator: Navigator = Navigator(

    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.home_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navigator.navController = navController
        NavigationUI.setupWithNavController(binding.bottomNav, navController)
    }

    override fun navigateToFlow(flow: NavigationFlow) {
        navigator.navigateToFlow(flow)
    }

}