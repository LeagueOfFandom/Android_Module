package com.soma.lof.common.ui

import com.soma.lof.common.data.entity.CommonItem
import com.soma.lof.common.data.entity.MatchPreviewTextViewObject
import com.soma.lof.common.databinding.ItemMatchPreviewTextBinding

class MatchPreviewTextVH(
    private val binding: ItemMatchPreviewTextBinding,
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val data = item.viewObject as MatchPreviewTextViewObject
        binding.viewObject = data
    }

}