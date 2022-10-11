package com.soma.lof.match.ui.match_info

import android.util.Log
import androidx.fragment.app.viewModels
import com.soma.lof.foundation.base.BaseFragment
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchScheduleInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchScheduleInfoFragment : BaseFragment<FragmentMatchScheduleInfoBinding>(R.layout.fragment_match_schedule_info) {

    private val viewModel: MatchInfoViewModel by viewModels()


    override fun initView() {
        Log.d("MatchInfoViewModel", "initView: ${viewModel.matchInfo.value}")

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