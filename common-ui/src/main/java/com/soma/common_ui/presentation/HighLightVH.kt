package com.soma.common_ui.presentation

import com.soma.common_ui.databinding.ItemHighlightViewBinding
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.entity.HighLightViewObject

class HighLightVH(
    private val binding: ItemHighlightViewBinding,
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as HighLightViewObject
        val videoAdapter = HighLightVideoAdapter()
        val testAdapter = TestVideoAdapter(viewObject.videoList)
        binding.viewObject = viewObject
        binding.adapter = testAdapter
    }
}