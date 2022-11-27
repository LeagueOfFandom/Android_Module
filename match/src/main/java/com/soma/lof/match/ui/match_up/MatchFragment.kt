package com.soma.lof.match.ui.match_up

import android.os.Build
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.soma.common.ui.base.BaseFragment
import com.soma.common.ui.presentation.CommonListAdapter2
import com.soma.lof.core.model.entity.Month
import com.soma.lof.core.result.data
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber
import java.util.*

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

        lifecycleScope.launchWhenStarted {
            viewModel.matchData.collectLatest {
                matchListAdapter.submitList(it.data)

                if (binding.matchTeamSwitch.isChecked) {
                    binding.matchMsg.text = getString(R.string.match_no_selected_team_msg)
                } else {
                    binding.matchMsg.text = getString(R.string.match_no_data_msg)
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.dateChangeFlow.collectLatest { isChanged ->
                Timber.tag("check@@@").d("collect")
                if (isChanged) {
                    val language = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        val primaryLocale: Locale = requireContext().resources.configuration.locales.get(0)
                        primaryLocale.language
                    } else {
                        Locale.getDefault().language
                    }

                    if (language == "ko") {
                        binding.matchMonth.text = getString(R.string.date_format_m_d, viewModel.todayYear, viewModel.todayMonth)
                    } else {
                        binding.matchMonth.text = getString(R.string.date_format_m_d, Month.values()[viewModel.todayMonth.toInt()-1], viewModel.todayYear)
                    }

                    viewModel.getMatchList(binding.matchTeamSwitch.isChecked)
                    viewModel.dateChangeFlow.value = false
                }
            }
        }
    }
}