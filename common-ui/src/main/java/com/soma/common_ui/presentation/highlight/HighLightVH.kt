package com.soma.common_ui.presentation.highlight

import com.soma.common_ui.databinding.ItemHighlightViewBinding
import com.soma.common_ui.presentation.CommonVH
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.entity.HighLightVO

class HighLightVH(
    private val binding: ItemHighlightViewBinding,
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as HighLightVO
        val videoAdapter = HighLightVideoAdapter()
        val testAdapter = TestVideoAdapter(viewObject.videoList)
        binding.viewObject = viewObject
        binding.adapter = testAdapter
    }
}