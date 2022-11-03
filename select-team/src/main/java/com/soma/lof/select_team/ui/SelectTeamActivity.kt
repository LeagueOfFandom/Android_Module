package com.soma.lof.select_team.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.soma.common.ui.base.BaseActivity
import com.soma.lof.select_team.R
import com.soma.lof.select_team.databinding.ActivitySelectTeamBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class SelectTeamActivity : BaseActivity<ActivitySelectTeamBinding>(R.layout.activity_select_team) {

    private val viewModel by viewModels<SelectTeamViewModel>()
    private val teamLeagueAdapter: SelectTeamLeagueAdapter by lazy {
        SelectTeamLeagueAdapter(this@SelectTeamActivity, viewModel)
    }

    @Inject
    @Named("Main")
    lateinit var mainActivityClass: Class<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        bind {
            vm = viewModel
        }

        lifecycleScope.launchWhenCreated {
            viewModel.tabItems.collectLatest {
                if (it.isNotEmpty()) {
                    binding.selectTeamVp.apply {
                        adapter = teamLeagueAdapter
                        orientation = ViewPager2.ORIENTATION_HORIZONTAL
                    }

                    TabLayoutMediator(binding.selectTeamTabLayout, binding.selectTeamVp) { tab, position ->
                        tab.text = it[position]
                    }.attach()
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.navigateHome.collectLatest { isNavigate ->
                if (isNavigate) navigateHome()
            }
        }
    }

    private fun navigateHome() {
        val intent = Intent(this@SelectTeamActivity, mainActivityClass).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }

    companion object {
        const val TAG = "SelectTeamActivity"
    }
}