package com.soma.common_ui.presentation

import com.soma.common_ui.databinding.ItemMatchLiveBinding
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.entity.MatchVO

class MatchLiveVH(
    private val binding: ItemMatchLiveBinding
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as MatchVO
        binding.viewObject = viewObject
    }
}