package com.soma.lof.home.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.soma.lof.foundation.result.data

class HomeBannerAdapter(fragment: Fragment, private val viewModel: HomeViewModel) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return HomeBannerFragment.newInstance(position)
    }
}