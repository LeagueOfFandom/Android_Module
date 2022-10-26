package com.soma.common.ui.presentation

import com.soma.common.databinding.ItemOneLineTextBinding
import com.soma.lof.core.model.dto.CommonItem
import com.soma.lof.core.model.entity.TextVO

class OneLineTextVH(
    private val binding: ItemOneLineTextBinding,
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as TextVO
        binding.viewObject = viewObject
    }
}