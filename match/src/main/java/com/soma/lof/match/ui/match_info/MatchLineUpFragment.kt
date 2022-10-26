package com.soma.lof.match.ui.match_info

import android.graphics.Color
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.soma.common.ui.base.BaseFragment
import com.soma.lof.core.model.entity.Team
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchLineUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchLineUpFragment() : BaseFragment<FragmentMatchLineUpBinding>(
    R.layout.fragment_match_line_up) {

    private val viewModel by viewModels<MatchInfoViewModel>(ownerProducer = { requireParentFragment() })

    override fun initView() {
        initRoster()
    }

    private fun initRoster() {
        val mainData = viewModel.matchInfo.value.mainInfo
        val rosterObject = viewModel.matchInfo.value.rosterInfo
        val blueTeamColor = Color.parseColor((Team.valueOf(mainData.homeName!!).colorRGB))
        for (roster in rosterObject.blueTeam) {
            when (roster.position) {
                "top" -> {
                    binding.lineUpHomeTop.itemMatchLineUpName.text = roster.name
                    Glide.with(requireView())
                        .load(roster.positionImg)
                        .into(binding.lineUpHomeTop.itemMatchLineUpLaneLogo)
                    binding.lineUpHomeTop.itemMatchLineUpLaneLogo.setBackgroundColor(
                        blueTeamColor
                    )
                }
                "jun" -> {
                    binding.lineUpHomeJungle.itemMatchLineUpName.text = roster.name
                    Glide.with(requireView())
                        .load(roster.positionImg)
                        .into(binding.lineUpHomeJungle.itemMatchLineUpLaneLogo)
                    binding.lineUpHomeJungle.itemMatchLineUpLaneLogo.setBackgroundColor(
                        blueTeamColor
                    )
                }
                "mid" -> {
                    binding.lineUpHomeMid.itemMatchLineUpName.text = roster.name
                    Glide.with(requireView())
                        .load(roster.positionImg)
                        .into(binding.lineUpHomeMid.itemMatchLineUpLaneLogo)
                    binding.lineUpHomeMid.itemMatchLineUpLaneLogo.setBackgroundColor(
                        blueTeamColor
                    )
                }
                "adc" -> {
                    binding.lineUpHomeBot.itemMatchLineUpName.text = roster.name
                    Glide.with(requireView())
                        .load(roster.positionImg)
                        .into(binding.lineUpHomeBot.itemMatchLineUpLaneLogo)
                    binding.lineUpHomeBot.itemMatchLineUpLaneLogo.setBackgroundColor(
                        blueTeamColor
                    )
                }
                "sup" -> {
                    binding.lineUpHomeSup.itemMatchLineUpName.text = roster.name
                    Glide.with(requireView())
                        .load(roster.positionImg)
                        .into(binding.lineUpHomeSup.itemMatchLineUpLaneLogo)
                    binding.lineUpHomeSup.itemMatchLineUpLaneLogo.setBackgroundColor(
                        blueTeamColor
                    )
                }
            }
        }

        val redTeamColor = Color.parseColor(Team.valueOf(mainData.awayName!!).colorRGB)
        for (roster in rosterObject.redTeam) {
            when (roster.position) {
                "top" -> {
                    binding.lineUpAwayTop.itemMatchLineUpName.text = roster.name
                    Glide.with(requireView())
                        .load(roster.positionImg)
                        .into(binding.lineUpAwayTop.itemMatchLineUpLaneLogo)
                    binding.lineUpAwayTop.itemMatchLineUpLaneLogo.setBackgroundColor(redTeamColor)
                }
                "jun" -> {
                    binding.lineUpAwayJungle.itemMatchLineUpName.text = roster.name
                    Glide.with(requireView())
                        .load(roster.positionImg)
                        .into(binding.lineUpAwayJungle.itemMatchLineUpLaneLogo)
                    binding.lineUpAwayJungle.itemMatchLineUpLaneLogo.setBackgroundColor(redTeamColor)
                }
                "mid" -> {
                    binding.lineUpAwayMid.itemMatchLineUpName.text = roster.name
                    Glide.with(requireView())
                        .load(roster.positionImg)
                        .into(binding.lineUpAwayMid.itemMatchLineUpLaneLogo)
                    binding.lineUpAwayMid.itemMatchLineUpLaneLogo.setBackgroundColor(redTeamColor)
                }
                "adc" -> {
                    binding.lineUpAwayBot.itemMatchLineUpName.text = roster.name
                    Glide.with(requireView())
                        .load(roster.positionImg)
                        .into(binding.lineUpAwayBot.itemMatchLineUpLaneLogo)
                    binding.lineUpAwayBot.itemMatchLineUpLaneLogo.setBackgroundColor(redTeamColor)
                }
                "sup" -> {
                    binding.lineUpAwaySup.itemMatchLineUpName.text = roster.name
                    Glide.with(requireView())
                        .load(roster.positionImg)
                        .into(binding.lineUpAwaySup.itemMatchLineUpLaneLogo)
                    binding.lineUpAwaySup.itemMatchLineUpLaneLogo.setBackgroundColor(redTeamColor)
                }
            }
        }
    }

    companion object {
        fun newInstance() = MatchLineUpFragment()
    }
}