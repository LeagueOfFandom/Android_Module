package com.soma.common.ui.presentation

import com.soma.common.ui.databinding.ItemMatchLiveBinding
import com.soma.lof.core.model.dto.CommonItem
import com.soma.lof.core.model.entity.MatchVO

class MatchLiveVH(
    private val binding: ItemMatchLiveBinding
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as MatchVO
        binding.viewObject = viewObject
    }
}