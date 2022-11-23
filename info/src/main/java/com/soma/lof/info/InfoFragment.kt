package com.soma.lof.info

import androidx.fragment.app.viewModels
import com.soma.common.ui.base.BaseFragment
import com.soma.common.ui.presentation.CommonListAdapter2
import com.soma.lof.info.databinding.FragmentInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment : BaseFragment<FragmentInfoBinding>(R.layout.fragment_info) {

    private val viewModel by viewModels<InfoViewModel>()

    override fun initView() {
        bind {
            vm = viewModel
            adapter = CommonListAdapter2()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getUserInfoList()
    }
}