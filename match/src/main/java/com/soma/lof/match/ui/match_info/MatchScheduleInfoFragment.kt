package com.soma.lof.match.ui.match_info

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayoutMediator
import com.soma.common.ui.base.BaseFragment
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchScheduleInfoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MatchScheduleInfoFragment : BaseFragment<FragmentMatchScheduleInfoBinding>(R.layout.fragment_match_schedule_info) {

    private val viewModel: MatchInfoViewModel by viewModels()

    override fun initView() {
        bind {
            vpAdapter = MatchInfoAdapter(
                childFragmentManager,
                lifecycle
            )
        }

        lifecycleScope.launchWhenStarted {
            viewModel.matchDetailSetInfo.collectLatest {
                bind {
                    data = viewModel.matchDetailSetInfo.value
                }

                TabLayoutMediator(binding.matchScheduleTabLayout, binding.matchScheduleVp2) { tab, position ->
                    tab.text = when (position) {
                        0 -> "Preview"
                        1 -> "Prediction"
                        else -> "Line-up"
                    }
                }.attach()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MatchScheduleInfoFragment()
    }
}