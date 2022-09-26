package com.soma.lof.match.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MatchInfoAdapter(fragment: Fragment, private val matchId: Long, private val size: Int) : FragmentStateAdapter(fragment){

    override fun getItemCount(): Int = size

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> MatchPreviewFragment.newInstance(matchId);
            1 -> MatchPredictionFragment.newInstance(matchId);
            else -> MatchLineUpFragment.newInstance(matchId);
        }


}