package com.soma.common.ui.presentation

import com.soma.common.databinding.ItemTextArrowBinding
import com.soma.lof.core.model.dto.CommonItem
import com.soma.lof.core.model.entity.TextVO

class TextArrowVH(
    private val binding: ItemTextArrowBinding
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as TextVO
        binding.viewObject = viewObject
    }
}