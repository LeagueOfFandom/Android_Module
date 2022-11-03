package com.soma.lof.match.ui.match_info

import androidx.fragment.app.viewModels
import com.soma.common.ui.base.BaseFragment
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchResultInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchResultInfoFragment :
    BaseFragment<FragmentMatchResultInfoBinding>(R.layout.fragment_match_result_info) {

    private val viewModel: MatchInfoViewModel by viewModels()

    override fun initView() {
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