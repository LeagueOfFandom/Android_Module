package com.soma.lof.select_team.ui

import androidx.fragment.app.activityViewModels
import com.soma.common.base.BaseFragment
import com.soma.lof.select_team.R
import com.soma.lof.select_team.databinding.FragmentSelectTeamListBinding
import com.soma.lof.select_team.util.TeamItemDecoration
import com.soma.lof.select_team.util.bindTeamItems

class SelectTeamListFragment(private val position: Int) : BaseFragment<FragmentSelectTeamListBinding>(R.layout.fragment_select_team_list) {

    private val viewModel by activityViewModels<SelectTeamViewModel>()
    private val selectTeamListAdapter: SelectTeamListAdapter by lazy {
        SelectTeamListAdapter(viewModel)
    }
    private val teamItemDecoration: TeamItemDecoration by lazy {
        TeamItemDecoration()
    }

    override fun initView() {
        selectTeamListAdapter.submitList(viewModel.leagueTeamInfo.value[position].teamList)

        bind {
            fake = viewModel.leagueTeamInfo.value[position]
            adapter = selectTeamListAdapter
            itemDecoration = teamItemDecoration
        }

    }
}