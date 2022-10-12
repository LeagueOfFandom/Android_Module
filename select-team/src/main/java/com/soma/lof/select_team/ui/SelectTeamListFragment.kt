package com.soma.lof.select_team.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.soma.lof.foundation.base.BaseFragment
import com.soma.lof.foundation.result.data
import com.soma.lof.select_team.R
import com.soma.lof.select_team.databinding.FragmentSelectTeamListBinding
import com.soma.lof.select_team.util.TeamItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class SelectTeamListFragment @Inject constructor() : BaseFragment<FragmentSelectTeamListBinding>(R.layout.fragment_select_team_list) {

    private val viewModel by activityViewModels<SelectTeamViewModel>()
    private lateinit var selectTeamListAdapter: SelectTeamListAdapter
    private val teamItemDecoration: TeamItemDecoration by lazy {
        TeamItemDecoration()
    }

    override fun initView() {
        val position = arguments?.getInt(POSITION_KEY) ?: 0

        selectTeamListAdapter = SelectTeamListAdapter(viewModel, position)

        bind {
            vm = viewModel
            pos = position
            adapter = selectTeamListAdapter
            itemDecoration = teamItemDecoration
        }
    }

    companion object {
        const val POSITION_KEY = "position"

        fun newInstance(position: Int) : Fragment {
            val fragment = SelectTeamListFragment()
            val bundle = Bundle()
            bundle.putInt(POSITION_KEY, position)
            fragment.arguments = bundle
            return fragment
        }
    }
}