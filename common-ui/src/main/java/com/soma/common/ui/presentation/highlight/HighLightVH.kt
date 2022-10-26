package com.soma.common.ui.presentation.highlight

import com.soma.common.databinding.ItemHighlightViewBinding
import com.soma.common.ui.presentation.CommonVH
import com.soma.lof.core.model.dto.CommonItem
import com.soma.lof.core.model.entity.HighLightVO

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