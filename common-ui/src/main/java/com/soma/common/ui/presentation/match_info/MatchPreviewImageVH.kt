package com.soma.common.ui.presentation.match_info

import com.soma.common.databinding.ItemMatchPreviewImageBinding
import com.soma.common.ui.presentation.CommonVH
import com.soma.lof.core.model.dto.CommonItem
import com.soma.lof.core.model.entity.MatchPreviewImageVO

class MatchPreviewImageVH(
    private val binding: ItemMatchPreviewImageBinding,
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val data = item.viewObject as MatchPreviewImageVO
        binding.viewObject = data
    }
}