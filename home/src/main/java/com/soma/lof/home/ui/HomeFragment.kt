package com.soma.lof.home.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.soma.common_ui.presentation.CommonListAdapter2
import com.soma.lof.foundation.base.BaseFragment
import com.soma.lof.foundation.result.data
import com.soma.lof.home.R
import com.soma.lof.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private var currentPage = 0
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var homeBannerAdapter: HomeBannerAdapter
    private lateinit var commonListAdapter: CommonListAdapter2

    override fun initView() {

        homeBannerAdapter = HomeBannerAdapter(this@HomeFragment, viewModel)
        commonListAdapter = CommonListAdapter2()

        Log.d(TAG, "initView homeData 있니?: ${viewModel.homeData.value}")
        Log.d(TAG, "initView bannerAdapter 있니?: ${homeBannerAdapter.itemCount}")

        /* TODO Databinding할 때 Parameter specified as non-null is null: method kotlin.jvm.internal.Intrinsics.checkNotNullParameter, parameter state 라고 뜸 하지만 State에 Null이 들어올 수 없음.*/
        lifecycleScope.launchWhenCreated {
            viewModel.homeData.collectLatest {
                commonListAdapter.submitList(it.data?.commonItemList)

                binding.homeAdBanner.apply {
                    adapter = homeBannerAdapter
                    orientation = ViewPager2.ORIENTATION_HORIZONTAL
                }

                binding.indicator2.setViewPager(binding.homeAdBanner)

                bind {
                    rvAdapter = commonListAdapter
                    fragment = this@HomeFragment
                }
            }
        }

        initHomeUi()
    }

    private fun initHomeUi() {
        // 홈 배너 자동 스크롤
        CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                setPage()
                delay(3000)
            }
        }
    }

    private fun setPage() {
        binding.homeAdBanner.setCurrentItem(currentPage, true)
        currentPage = (currentPage + 1) % 6
    }

    companion object {
        const val TAG = "HomeFragment"
    }
}