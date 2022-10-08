package com.soma.common_ui.presentation.info

import com.soma.common_ui.databinding.ItemInfoPostLikeBinding
import com.soma.common_ui.presentation.CommonVH
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.entity.InfoViewObject

class InfoPostLikeVH(
    private val binding: ItemInfoPostLikeBinding,
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as InfoViewObject
        binding.viewObject = viewObject
    }
}