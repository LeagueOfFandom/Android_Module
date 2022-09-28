package com.soma.common_ui.presentation

import com.soma.common_ui.databinding.ItemCommunityBinding
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.entity.CommunityViewObject

class CommunityVH(
    private val binding: ItemCommunityBinding,
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as CommunityViewObject
        binding.viewObject = viewObject
    }
}