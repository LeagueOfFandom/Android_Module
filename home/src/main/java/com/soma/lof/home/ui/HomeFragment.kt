package com.soma.lof.home.ui

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.soma.common.base.BaseFragment
import com.soma.lof.common.route.FeatureMatchInfoRouteContract
import com.soma.lof.common.ui.CommonListAdapter
import com.soma.lof.home.R
import com.soma.lof.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private var currentPage = 0
    private val viewModel by viewModels<HomeViewModel>()

    val homeAdapter by lazy {
        CommonListAdapter(viewModel.homeData.value)
    }

    @Inject
    lateinit var matchInfoRouteContract: FeatureMatchInfoRouteContract
    private var homeBannerAdapter: HomeBannerAdapter? = null

    override fun initView() {
        Log.d(TAG, "initView: ${viewModel.homeData.value}")

        bind {
            adapter = homeAdapter
            fragment = this@HomeFragment
        }

        homeBannerAdapter = HomeBannerAdapter(this@HomeFragment, viewModel.homeBannerData.size)
        binding.homeAdBanner.apply {
            adapter = homeBannerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }

        binding.indicator2.setViewPager(binding.homeAdBanner)

        CoroutineScope(Dispatchers.Main).launch {
            while(true) {
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

    fun move() {
        Log.d(TAG, "move: 클릭 중")
    }


}