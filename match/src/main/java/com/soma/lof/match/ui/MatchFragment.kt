package com.soma.lof.match.ui

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.soma.common.base.BaseFragment
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MatchFragment : BaseFragment<FragmentMatchBinding>(R.layout.fragment_match) {

    private val viewModel by viewModels<MatchViewModel>()
    private var matchDateListAdapter: MatchDateListAdapter? = null

    override fun initView() {

        matchDateListAdapter = MatchDateListAdapter(this@MatchFragment, viewModel)

        lifecycleScope.launchWhenCreated {
            viewModel.matchData.collectLatest {

                binding.matchViewpager.apply {
                    adapter = matchDateListAdapter
                    orientation = ViewPager2.ORIENTATION_HORIZONTAL
                }

                binding.matchMonth.text = it.dateList[0].month

                TabLayoutMediator(binding.matchTabLayout,
                    binding.matchViewpager) { tab, position ->
                    val dateInfo = it.dateList[position]
                    val spanText = SpannableStringBuilder(dateInfo.date)
                    spanText.setSpan(ForegroundColorSpan(Color.parseColor(dateInfo.color)), 0, dateInfo.date.length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
                    tab.text = spanText
                }.attach()
            }
        }
    }

    companion object {
        const val TAG = "MatchFragment"

        fun newInstance(): Fragment = MatchFragment()
    }
}