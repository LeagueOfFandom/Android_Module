package com.soma.lof.match.ui.match_info

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.soma.common.ui.base.BaseFragment
import com.soma.common.ui.presentation.CommonListAdapter
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchPreviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchPreviewFragment() : BaseFragment<FragmentMatchPreviewBinding>(R.layout.fragment_match_preview) {

    private val viewModel: MatchInfoViewModel by viewModels(ownerProducer = { requireParentFragment() })
    private lateinit var matchInfoPreviewAdapter: CommonListAdapter

    override fun initView() {
        matchInfoPreviewAdapter = CommonListAdapter(viewModel.matchInfo.value.setInfo)

        bind {
            adapter = matchInfoPreviewAdapter
        }
    }

    companion object {
        fun newInstance(): Fragment = MatchPreviewFragment()

    }
}