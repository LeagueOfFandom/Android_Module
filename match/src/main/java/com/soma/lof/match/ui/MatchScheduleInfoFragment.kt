package com.soma.lof.match.ui

import com.soma.common.base.BaseFragment
import com.soma.lof.core_model.entity.MatchViewObject
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchScheduleInfoBinding

class MatchScheduleInfoFragment : BaseFragment<FragmentMatchScheduleInfoBinding>(R.layout.fragment_match_schedule_info) {

    private val matchViewObject = MatchViewObject(
        1L, "Home", "", "Away", "", "2022-03-04", "16:00", "LCK", false, 1, 0, true
    )

    override fun initView() {
        bind {
            viewObject = matchViewObject
            vpAdapter = MatchInfoAdapter(this@MatchScheduleInfoFragment, 0L, 3)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = MatchScheduleInfoFragment()
    }


}