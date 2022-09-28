package com.soma.lof.home.ui

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.soma.common.base.BaseFragment
import com.soma.common_ui.presentation.TextArrowViewObject
import com.soma.common_ui.presentation.CommonListAdapter
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.entity.CommunityViewObject
import com.soma.lof.core_model.entity.HighLightViewObject
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

    private lateinit var homeAdapter: CommonListAdapter

    private var homeBannerAdapter: HomeBannerAdapter? = null

    override fun initView() {
        Log.d(TAG, "initView: ${viewModel.homeData.value}")

//        homeAdapter = CommonListAdapter()

        bind {
//            adapter = homeAdapter
//            adapter = CommonListAdapter(viewModel.homeData.value)
            fragment = this@HomeFragment
        }

        val dummy = listOf(
            CommonItem(
                "TEXT_ARROW_VIEW",
                TextArrowViewObject("실시간 인기글")
            ),
            CommonItem(
                "COMMUNITY_VIEW",
                CommunityViewObject("testNickname",
                    "https://lh3.googleusercontent.com/a/ALm5wu0owKbQ9im6-ViZ9WKUHt2RwqGVLlx1i59ex1CZ=s96-c",
                    "2022-09-28T15:05:21.381546431",
                    "test 블라블라")
            ),
            CommonItem(
                "TEXT_ARROW_VIEW",
                TextArrowViewObject("실시간 인기글")
            ),
            CommonItem(
                "HIGHLIGHT_VIEW",
                HighLightViewObject(listOf(
                    "fiY08uGY3dM", "fiY08uGY3dM"
                )
                )
            )
        )
       /* binding.homeRv.apply {
            adapter = CommonListAdapter(dummy)
        }*/

        homeBannerAdapter = HomeBannerAdapter(this@HomeFragment, viewModel.homeBannerData.size)
        binding.homeAdBanner.apply {
            adapter = homeBannerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
        binding.indicator2.setViewPager(binding.homeAdBanner)

        initHomeUi()
    }

    private fun initHomeUi() {
        lifecycleScope.launchWhenStarted {
            viewModel.homeData.collectLatest {

                binding.homeRv.apply {
                    adapter = CommonListAdapter(it)
                }
//                val data = it
//                homeAdapter.submitList(it)
            }
        }

        // 홈 배너 자동 스크롤
        CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                setPage()
                delay(3000)
            }
        }
    }

    companion object {
        fun newInstance() = HomeFragment()

        const val TAG = "HomeFragment"
    }

    fun setPage() {
        binding.homeAdBanner.setCurrentItem(currentPage, true)
        currentPage = (currentPage + 1) % 6
    }
}