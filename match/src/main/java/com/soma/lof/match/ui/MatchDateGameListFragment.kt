package com.soma.lof.match.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.soma.common.base.BaseFragment
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchDateGameListBinding


class MatchDateGameListFragment() : BaseFragment<FragmentMatchDateGameListBinding>(R.layout.fragment_match_date_game_list) {

    private val viewModel by viewModels<MatchViewModel>(ownerProducer = { requireParentFragment() })
    private val matchDateGameAdapter: MatchDateGameAdapter by lazy {
        MatchDateGameAdapter(viewModel)
    }

    override fun initView() {
        val position = arguments?.getInt(POSITION_KEY) ?: 0

        matchDateGameAdapter.submitList(viewModel.matchData.value.matchDataList[position])

        bind {
            adapter = matchDateGameAdapter
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