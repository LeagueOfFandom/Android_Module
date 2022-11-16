package com.soma.lof.match.ui.match_info

import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.soma.common.ui.base.BaseFragment
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchResultInfoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MatchResultInfoFragment :
    BaseFragment<FragmentMatchResultInfoBinding>(R.layout.fragment_match_result_info) {

    private val viewModel: MatchInfoViewModel by viewModels()

    override fun initView() {

        val safeArgs: MatchScheduleInfoFragmentArgs by navArgs()
        val matchId = safeArgs.matchId

        viewModel.getMatchDetail(matchId)

        bind {
            vpAdapter = MatchInfoAdapter(
                childFragmentManager,
                lifecycle,
            )
        }
        lifecycleScope.launchWhenStarted {
            viewModel.matchDetailSetInfo.collectLatest {
                bind {
                    data = viewModel.matchDetailSetInfo.value
                    setAdapter = MatchSetAdapter(requireContext(), viewModel)
                }

                val blueDefaultTeamAreaColor = ResourcesCompat.getDrawable(resources, R.drawable.bg_match_info_blue_default_team_color, null)
                val blueTeamArea = ResourcesCompat.getDrawable(resources, R.drawable.bg_match_info_blue_team_color, null)
                val redDefaultTeamArea = ResourcesCompat.getDrawable(resources, R.drawable.bg_match_info_red_default_team_color, null)
                val redTeamArea = ResourcesCompat.getDrawable(resources, R.drawable.bg_match_info_red_team_color, null)

                binding.matchResultBlueArea.background = if (it?.teamVsTeamMainInfo?.blueWin == true) blueTeamArea else blueDefaultTeamAreaColor
                binding.matchResultRedArea.background = if (it?.teamVsTeamMainInfo?.redWin == true) redTeamArea else redDefaultTeamArea

                TabLayoutMediator(binding.matchResultTabLayout, binding.matchResultVp2) { tab, position ->
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
        fun newInstance() = MatchResultInfoFragment()
    }
}