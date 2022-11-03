package com.soma.lof.select_team.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.soma.common.ui.base.BaseFragment
import com.soma.lof.select_team.R
import com.soma.lof.select_team.databinding.FragmentSelectTeamListBinding
import com.soma.lof.select_team.util.TeamItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SelectTeamListFragment @Inject constructor() : BaseFragment<FragmentSelectTeamListBinding>(R.layout.fragment_select_team_list) {

    private val viewModel by activityViewModels<SelectTeamViewModel>()
    private lateinit var selectTeamListAdapter: SelectTeamListAdapter

    override fun initView() {
        val position = arguments?.getInt(POSITION_KEY) ?: 0

        selectTeamListAdapter = SelectTeamListAdapter(viewModel, position)

        bind {
            vm = viewModel
            pos = position
            adapter = selectTeamListAdapter
            itemDecoration = TeamItemDecoration()
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