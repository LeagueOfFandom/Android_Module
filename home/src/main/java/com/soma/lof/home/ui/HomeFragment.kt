package com.soma.lof.home.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.soma.common.ui.base.BaseFragment
import com.soma.common.ui.presentation.CommonListAdapter2
import com.soma.lof.core.model.entity.LiveMatchScoreEvent
import com.soma.lof.core.result.data
import com.soma.lof.home.R
import com.soma.lof.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private var currentPage = 0
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var homeBannerAdapter: HomeBannerAdapter
    private lateinit var commonListAdapter: CommonListAdapter2

    override fun initView() {

        homeBannerAdapter = HomeBannerAdapter(this@HomeFragment, viewModel.homeModelData.value.data?.bannerList?.size ?: 0)
        commonListAdapter = CommonListAdapter2(requireActivity())

        bind {
            vm = viewModel
            rvAdapter = commonListAdapter
        }

        subscribeUI()
        setAutoBannerPass()
    }

    private fun subscribeUI() {
        lifecycleScope.launchWhenCreated {
            viewModel.homeModelData.collectLatest {
                binding.homeAdBanner.apply {
                    adapter = homeBannerAdapter
                    orientation = ViewPager2.ORIENTATION_HORIZONTAL
                }

                binding.indicator2.setViewPager(binding.homeAdBanner)
            }
        }
    }

    private fun setAutoBannerPass() {
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

    /** Changes in LiveScore are detected by [EventBus] */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun updateLiveMatchScore(event: LiveMatchScoreEvent) {
        viewModel.getHomeData()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    companion object {
        const val TAG = "HomeFragment"
    }
}