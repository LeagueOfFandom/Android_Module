package com.soma.lof.home.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeBannerAdapter(fragment: Fragment, private val size: Int) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = size

    override fun createFragment(position: Int): Fragment {
        return HomeBannerFragment.newInstance(position)
    }
}