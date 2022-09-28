package com.soma.common_ui.presentation

import com.soma.common_ui.databinding.ItemInfoDefaultViewBinding
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.entity.InfoDefaultViewObject

class InfoDefaultVH(
    private val binding: ItemInfoDefaultViewBinding,
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as InfoDefaultViewObject
        binding.viewObject = viewObject
    }
}