package com.soma.lof.match.ui

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MatchDateListAdapter(fa: Fragment, private val viewModel: MatchViewModel) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = viewModel.matchData.value.dateList.size

    override fun createFragment(position: Int): Fragment {
        return MatchDateGameListFragment.newInstance(position)
    }
}