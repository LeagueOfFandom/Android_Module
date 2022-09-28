package com.soma.lof.info

import com.soma.common.base.BaseFragment
import com.soma.common_ui.presentation.CommonListAdapter
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.entity.InfoDefaultViewObject
import com.soma.lof.core_model.entity.OneLineTextViewObject
import com.soma.lof.info.databinding.FragmentInfoBinding

class InfoFragment : BaseFragment<FragmentInfoBinding>(R.layout.fragment_info) {

    private val dataSet = listOf(
        CommonItem("ONE_LINE_TEXT_VIEW",
            OneLineTextViewObject("오늘")),
        CommonItem("INFO_DEFAULT_VIEW",
            InfoDefaultViewObject("체크", true)),
        CommonItem("ONE_LINE_TEXT_VIEW",
            OneLineTextViewObject("어제")),
        CommonItem("INFO_DEFAULT_VIEW",
            InfoDefaultViewObject("체크", false)),
        CommonItem("INFO_DEFAULT_VIEW",
            InfoDefaultViewObject("체크", true)),
        CommonItem("ONE_LINE_TEXT_VIEW",
            OneLineTextViewObject("이번주")),
        CommonItem("INFO_DEFAULT_VIEW",
            InfoDefaultViewObject("체크", false)),
        CommonItem("INFO_DEFAULT_VIEW",
            InfoDefaultViewObject("체크", true)),
    )

    override fun initView() {
        bind {
            adapter = CommonListAdapter(dataSet)
        }
    }

    companion object {
        fun newInstance() = InfoFragment()
    }

}