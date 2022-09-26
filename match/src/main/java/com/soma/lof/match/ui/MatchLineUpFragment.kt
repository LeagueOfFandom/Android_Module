package com.soma.lof.match.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.soma.common.base.BaseFragment
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchLineUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchLineUpFragment() : BaseFragment<FragmentMatchLineUpBinding>(
    R.layout.fragment_match_line_up) {
//    private val viewModel by viewModels<MatchInfoViewModel>(ownerProducer = { requireParentFragment() })

    override fun initView() {

    }


//        super.onViewCreated(view, savedInstanceState)
//        // binding.viewModel = viewModel
//
////        Log.d("MatchLineUpFragment", "onViewCreated: ${viewModel.matchInfoSetData.value?.rosterInfo}")
////        initRoster()
////
////        viewModel.matchInfoSetData.observe(viewLifecycleOwner) {
////            Log.d("MatchLineUpFragment", "onViewCreated: Roster LiveData")
////            initRoster()
////        }

    override fun onStart() {
        super.onStart()
//        if (viewModel.matchInfoSetData.value == null) {
//            viewModel.getMatchInfo(matchId)
//        }
    }

    /*private fun initRoster() {
        val mainData = viewModel.matchInfoSetData.value?.mainInfo
        val rosterObject = viewModel.matchInfoSetData.value?.rosterInfo
        if (mainData != null && rosterObject != null) {
            val blueTeamColor = getColor(Team.valueOf(mainData.blueTeamName).color)
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

            val redTeamColor = getColor(Team.valueOf(mainData.redTeamName).color)
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
    }*/

    companion object {
        fun newInstance(matchId: Long) : Fragment {
            val fragment = MatchLineUpFragment()
            val bundle = Bundle()
            bundle.putLong("MATCH_ID", matchId)
            fragment.arguments = bundle
            return fragment
        }
    }
}