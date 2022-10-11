package com.soma.common_ui.presentation

import com.soma.common_ui.databinding.ItemMatchPreviewImageBinding
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.entity.MatchPreviewImageVO

class MatchPreviewImageVH(
    private val binding: ItemMatchPreviewImageBinding,
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val data = item.viewObject as MatchPreviewImageVO
        binding.viewObject = data
    }
}