package com.soma.lof.community

import com.soma.lof.community.databinding.FragmentCommunityBinding
import com.soma.common_ui.base.BaseFragment

class CommunityFragment : BaseFragment<FragmentCommunityBinding>(R.layout.fragment_community) {

    override fun initView() {
    }

    companion object {
        fun newInstance() = CommunityFragment()
    }
}