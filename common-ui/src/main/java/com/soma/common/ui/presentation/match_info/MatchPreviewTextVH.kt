package com.soma.common.ui.presentation.match_info

import com.soma.common.ui.databinding.ItemMatchPreviewTextBinding
import com.soma.common.ui.presentation.CommonVH
import com.soma.lof.core.model.dto.CommonItem
import com.soma.lof.core.model.entity.MatchPreviewTextVO

class MatchPreviewTextVH(
    private val binding: ItemMatchPreviewTextBinding,
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val data = item.viewObject as MatchPreviewTextVO
        binding.viewObject = data
    }

}