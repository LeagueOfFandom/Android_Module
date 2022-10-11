package com.soma.lof.match.ui.match_up

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.soma.common_ui.presentation.CommonListAdapter
import com.soma.lof.foundation.base.BaseFragment
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.text.SimpleDateFormat

@AndroidEntryPoint
class MatchFragment : BaseFragment<FragmentMatchBinding>(R.layout.fragment_match) {

    private val viewModel: MatchViewModel by viewModels()
    private var matchListAdapter: CommonListAdapter? = null

    override fun initView() {

        // MatchList Data 호출
        viewModel.getMatchList(convertTimestampToDate(), false)
//        viewModel.getMatchList(", false)

        binding.matchMonth.text = convertTimestampToMonthDate()



        lifecycleScope.launchWhenCreated {
            viewModel.matchData.collectLatest {
                bind {
                    adapter = CommonListAdapter(it)
                }
            }
        }
    }

    private fun convertTimestampToMonthDate() : String{
        val currentTime = System.currentTimeMillis()
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(currentTime)
    }

    private fun convertTimestampToDate() : String{
        val currentTime = System.currentTimeMillis()
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(currentTime)
    }

    companion object {
        const val TAG = "MatchFragment"

        fun newInstance(): Fragment = MatchFragment()
    }
}