package com.soma.lof.match.ui

import android.graphics.Color
import android.os.Build
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.soma.common.base.BaseFragment
import com.soma.common_ui.presentation.CommonListAdapter
import com.soma.common_ui.presentation.CommonListAdapter2
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime

@AndroidEntryPoint
class MatchFragment : BaseFragment<FragmentMatchBinding>(R.layout.fragment_match) {

    private val viewModel by viewModels<MatchViewModel>()
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