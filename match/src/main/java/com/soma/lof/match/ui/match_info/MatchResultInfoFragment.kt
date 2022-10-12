package com.soma.lof.match.ui.match_info

import android.util.Log
import androidx.fragment.app.viewModels
import com.soma.lof.foundation.base.BaseFragment
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchResultInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchResultInfoFragment :
    BaseFragment<FragmentMatchResultInfoBinding>(R.layout.fragment_match_result_info) {

    private val viewModel: MatchInfoViewModel by viewModels()

    override fun initView() {

        Log.d("MatchInfoViewModel", "initView: ${viewModel.matchInfo.value}")
        bind {
            data = viewModel.matchInfo.value.mainInfo
            setAdapter = MatchSetAdapter(requireContext())
            vpAdapter = MatchInfoAdapter(
                childFragmentManager,
                lifecycle,
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MatchResultInfoFragment()
    }

}