package com.soma.lof.common.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.soma.lof.common.data.entity.CommonItem
import com.soma.lof.common.data.entity.HighLightViewObject
import com.soma.lof.common.databinding.ItemHighlightViewBinding

class HighLightVH(
    private val binding: ItemHighlightViewBinding,
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as HighLightViewObject
        val videoAdapter = HighLightVideoAdapter()
        val testAdapter = TestVideoAdapter(viewObject.youtubeVideoIdList)
        binding.viewObject = viewObject
        binding.adapter = testAdapter
    }
}