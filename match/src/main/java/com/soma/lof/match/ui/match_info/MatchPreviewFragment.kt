package com.soma.lof.match.ui.match_info

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.soma.common.ui.base.BaseFragment
import com.soma.common.ui.presentation.CommonListAdapter
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchPreviewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MatchPreviewFragment() : BaseFragment<FragmentMatchPreviewBinding>(R.layout.fragment_match_preview) {

    private val viewModel: MatchInfoViewModel by viewModels(ownerProducer = { requireParentFragment() })
    private lateinit var matchInfoPreviewAdapter: MatchInfoPreviewAdapter

    override fun initView() {
        matchInfoPreviewAdapter = MatchInfoPreviewAdapter()

        bind {
            vm = viewModel
            adapter = matchInfoPreviewAdapter
            itemDecoration = MatchInfoPreviewItemDecoration()
        }
    }

    companion object {
        fun newInstance(): Fragment = MatchPreviewFragment()

    }
}