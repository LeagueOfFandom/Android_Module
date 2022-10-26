package com.soma.lof.select_team.ui

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

@AndroidEntryPoint
class SelectTeamActivity : BaseActivity<ActivitySelectTeamBinding>(R.layout.activity_select_team) {

    private val viewModel by viewModels<SelectTeamViewModel>()
    private val teamLeagueAdapter: SelectTeamLeagueAdapter by lazy {
        SelectTeamLeagueAdapter(this@SelectTeamActivity, viewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        bind {
            activity = this@SelectTeamActivity
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
    }

    private fun navigateMain() {
        viewModel.navigateHome(this@SelectTeamActivity)
    }

    companion object {
        const val TAG = "SelectTeamActivity"
    }
}