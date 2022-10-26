package com.soma.lof.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.soma.common_ui.base.BaseFragment
import com.soma.lof.core_network.result.data
import com.soma.lof.home.R
import com.soma.lof.home.databinding.FragmentHomeBannerBinding

class HomeBannerFragment() :
    BaseFragment<FragmentHomeBannerBinding>(R.layout.fragment_home_banner) {

    private val viewModel by viewModels<HomeViewModel>(ownerProducer = { requireParentFragment() })

    override fun initView() {
        val position = arguments?.getInt(POSITION_KEY) ?: 0
        val bannerData = viewModel.homeData.value.data?.bannerList
        bind {
            imageUrl = bannerData?.get(position) ?: "https://cdn.imweb.me/upload/S201910012ff964777e0e3/62f9a36ea3cea.jpg"
        }
    }

    companion object {
        private const val POSITION_KEY = "position_key"

        fun newInstance(position: Int): Fragment {
            val fragment = HomeBannerFragment()
            val bundle = Bundle()
            bundle.putInt(POSITION_KEY, position)
            fragment.arguments = bundle
            return fragment
        }

    }
}