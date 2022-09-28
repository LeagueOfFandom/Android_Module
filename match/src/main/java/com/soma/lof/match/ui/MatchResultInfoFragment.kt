package com.soma.lof.match.ui

import com.soma.common.base.BaseFragment
import com.soma.lof.common.data.entity.MatchViewObject
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchResultInfoBinding

class MatchResultInfoFragment : BaseFragment<FragmentMatchResultInfoBinding>(R.layout.fragment_match_result_info) {


    override fun initView() {
        val matchViewObject = MatchViewObject(
            1L,"Home", "", "Away", "", "2022-03-04", "16:00", "LCK", false, 1, 0, true
        )
        bind {
            data = matchViewObject
            setAdapter = MatchSetAdapter(requireContext())
            vpAdapter = MatchInfoAdapter(this@MatchResultInfoFragment, 0L, 3)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MatchResultInfoFragment()
    }

}