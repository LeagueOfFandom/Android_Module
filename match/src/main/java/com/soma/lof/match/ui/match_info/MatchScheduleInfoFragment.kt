package com.soma.lof.match.ui.match_info

import androidx.fragment.app.viewModels
import com.soma.common.ui.base.BaseFragment
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchScheduleInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchScheduleInfoFragment : BaseFragment<FragmentMatchScheduleInfoBinding>(R.layout.fragment_match_schedule_info) {

    private val viewModel: MatchInfoViewModel by viewModels()

    override fun initView() {
        bind {
            data = viewModel.matchInfo.value.mainInfo
            vpAdapter = MatchInfoAdapter(
                childFragmentManager,
                lifecycle,
            )
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = MatchScheduleInfoFragment()
    }
}