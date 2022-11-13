package com.soma.lof.match.ui.match_up

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.soma.common.ui.base.BaseFragment
import com.soma.common.ui.presentation.CommonListAdapter2
import com.soma.lof.core.result.data
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@AndroidEntryPoint
class MatchFragment : BaseFragment<FragmentMatchBinding>(R.layout.fragment_match) {

    private val viewModel: MatchViewModel by viewModels()
    private lateinit var matchListAdapter: CommonListAdapter2

    override fun initView() {

        matchListAdapter = CommonListAdapter2()

        bind {
            vm = viewModel
            adapter = matchListAdapter
        }

        lifecycleScope.launchWhenCreated {
            viewModel.matchData.collectLatest {
                matchListAdapter.submitList(it.data)
            }
        }

        // API 상의 후 변경 예정
        binding.matchMonth.text = viewModel.todayDate.value
    }
}