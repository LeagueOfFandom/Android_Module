package com.soma.lof.select_team.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SelectTeamLeagueAdapter(fa: FragmentActivity, private val count: Int) : FragmentStateAdapter(fa){

    override fun getItemCount(): Int = count

    override fun createFragment(position: Int): Fragment = SelectTeamListFragment(position)
}