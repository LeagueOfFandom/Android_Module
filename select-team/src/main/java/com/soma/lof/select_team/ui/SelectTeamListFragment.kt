package com.soma.lof.select_team.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.soma.common.base.BaseFragment
import com.soma.lof.select_team.R
import com.soma.lof.select_team.databinding.FragmentSelectTeamListBinding
import com.soma.lof.select_team.util.TeamItemDecoration
import com.soma.lof.select_team.util.bindTeamItems

class SelectTeamListFragment() : BaseFragment<FragmentSelectTeamListBinding>(R.layout.fragment_select_team_list) {

    private val viewModel by activityViewModels<SelectTeamViewModel>()
    private val selectTeamListAdapter: SelectTeamListAdapter by lazy {
        SelectTeamListAdapter(viewModel)
    }
    private val teamItemDecoration: TeamItemDecoration by lazy {
        TeamItemDecoration()
    }

    override fun initView() {
        val position = arguments?.getInt(POSITION_KEY) ?: 0
        selectTeamListAdapter.submitList(viewModel.leagueTeamInfo.value[position].teamInfo)

        bind {
            fake = viewModel.leagueTeamInfo.value[position]
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