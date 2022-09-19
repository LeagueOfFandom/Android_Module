package com.soma.lof.select_team.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SelectTeamLeagueAdapter(fa: FragmentActivity, private val viewModel: SelectTeamViewModel) : FragmentStateAdapter(fa){

    override fun getItemCount(): Int = viewModel.tabItems.value.size

    override fun createFragment(position: Int): Fragment = SelectTeamListFragment.newInstance(position)
}