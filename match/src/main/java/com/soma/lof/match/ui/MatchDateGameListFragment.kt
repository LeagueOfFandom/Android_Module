package com.soma.lof.match.ui

import android.util.Log
import androidx.fragment.app.viewModels
import com.soma.common.base.BaseFragment
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchDateGameListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MatchDateGameListFragment @Inject constructor(
    private val position: Int,
) : BaseFragment<FragmentMatchDateGameListBinding>(R.layout.fragment_match_date_game_list) {

    private val viewModel by viewModels<MatchViewModel>(ownerProducer = { requireParentFragment() })
    private val matchDateGameAdapter: MatchDateGameAdapter by lazy {
        MatchDateGameAdapter(viewModel)
    }

    override fun initView() {
        matchDateGameAdapter.submitList(viewModel.matchData.value.matchDataList[position])

        bind {
            adapter = matchDateGameAdapter
        }
    }

    companion object {
        const val TAG = "MatchDateGameListFragment"
    }

}