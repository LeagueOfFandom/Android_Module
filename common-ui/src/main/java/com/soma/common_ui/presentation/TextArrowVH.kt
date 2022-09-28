package com.soma.common_ui.presentation

import com.soma.common_ui.databinding.ItemTextArrowBinding
import com.soma.lof.core_model.dto.CommonItem

class TextArrowVH(
    private val binding: ItemTextArrowBinding
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as TextArrowViewObject
        binding.viewObject = viewObject
    }
}