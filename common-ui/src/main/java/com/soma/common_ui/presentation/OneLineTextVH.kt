package com.soma.common_ui.presentation

import com.soma.common_ui.databinding.ItemOneLineTextBinding
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.entity.OneLineTextVO

class OneLineTextVH(
    private val binding: ItemOneLineTextBinding,
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as OneLineTextVO
        binding.viewObject = viewObject
    }
}