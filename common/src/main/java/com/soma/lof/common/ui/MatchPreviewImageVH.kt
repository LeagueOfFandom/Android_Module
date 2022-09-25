package com.soma.lof.common.ui

import com.soma.lof.common.data.entity.CommonItem
import com.soma.lof.common.data.entity.MatchPreviewImageViewObject
import com.soma.lof.common.databinding.ItemMatchPreviewImageBinding

class MatchPreviewImageVH(
    private val binding: ItemMatchPreviewImageBinding,
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val data = item.viewObject as MatchPreviewImageViewObject
        binding.viewObject = data
    }
}