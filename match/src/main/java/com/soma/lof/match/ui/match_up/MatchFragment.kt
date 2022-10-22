package com.soma.lof.match.ui.match_up

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.soma.common_ui.presentation.CommonListAdapter2
import com.soma.lof.foundation.base.BaseFragment
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
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

        // API 상의 후 변경 예정
        binding.matchMonth.text = convertTimestampToMonthDate()
    }

    private fun convertTimestampToMonthDate() : String{
        val currentTime = System.currentTimeMillis()
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
        return sdf.format(currentTime)
    }

    private fun convertTimestampToDate() : String{
        val currentTime = System.currentTimeMillis()
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
        return sdf.format(currentTime)
    }

    companion object {
        const val TAG = "MatchFragment"

        fun newInstance(): Fragment = MatchFragment()
    }
}