package com.soma.lof.common.ui

import com.soma.lof.common.data.entity.CommonItem
import com.soma.lof.common.data.entity.TextArrowViewObject
import com.soma.lof.common.databinding.ItemTextArrowBinding

class TextArrowVH(
    private val binding: ItemTextArrowBinding
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as TextArrowViewObject
        binding.viewObject = viewObject
    }
}