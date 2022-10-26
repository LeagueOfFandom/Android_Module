package com.soma.lof.info

import androidx.fragment.app.viewModels
import com.soma.common_ui.presentation.CommonListAdapter
import com.soma.common_ui.base.BaseFragment
import com.soma.lof.info.databinding.FragmentInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment : BaseFragment<FragmentInfoBinding>(R.layout.fragment_info) {

    private val viewModel by viewModels<InfoViewModel>()

    override fun initView() {
        bind {
            adapter = CommonListAdapter(viewModel.infoData.value)
        }
    }

    companion object {
        fun newInstance() = InfoFragment()
    }

}