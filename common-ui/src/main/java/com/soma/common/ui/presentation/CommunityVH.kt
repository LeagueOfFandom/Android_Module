package com.soma.common.ui.presentation

import com.soma.common.databinding.ItemCommunityBinding
import com.soma.lof.core.model.dto.CommonItem
import com.soma.lof.core.model.entity.CommunityVO

class CommunityVH(
    private val binding: ItemCommunityBinding,
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as CommunityVO
        binding.viewObject = viewObject
    }
}