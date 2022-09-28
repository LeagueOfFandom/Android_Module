package com.soma.lof.match.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.soma.common.base.BaseFragment
import com.soma.common_ui.presentation.CommonListAdapter
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchDateGameListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject



@AndroidEntryPoint
class MatchDateGameListFragment @Inject constructor() : BaseFragment<FragmentMatchDateGameListBinding>(R.layout.fragment_match_date_game_list) {

    private val viewModel by viewModels<MatchViewModel>(ownerProducer = { requireParentFragment() })
    private lateinit var matchDateGameAdapter: CommonListAdapter

    override fun initView() {
        val position = arguments?.getInt(POSITION_KEY) ?: 0

        matchDateGameAdapter = CommonListAdapter(viewModel.matchData.value.matchDataList[position])
        bind {
            adapter = matchDateGameAdapter
            size = viewModel.matchData.value.matchDataList[position].size
        }
    }

    companion object {
        const val TAG = "MatchDateGameListFragment"

        const val POSITION_KEY = "position"

        fun newInstance(position: Int) : Fragment {
            val fragment = MatchDateGameListFragment()
            val bundle = Bundle()
            bundle.putInt(POSITION_KEY, position)
            fragment.arguments = bundle
            return fragment
        }
    }

}