package com.soma.common_ui.presentation.match_info

import com.soma.common_ui.databinding.ItemMatchPreviewTextBinding
import com.soma.common_ui.presentation.CommonVH
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.entity.MatchPreviewTextVO

class MatchPreviewTextVH(
    private val binding: ItemMatchPreviewTextBinding,
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val data = item.viewObject as MatchPreviewTextVO
        binding.viewObject = data
    }

}