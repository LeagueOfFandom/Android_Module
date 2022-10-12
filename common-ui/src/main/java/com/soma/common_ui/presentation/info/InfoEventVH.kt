package com.soma.common_ui.presentation.info

import com.soma.common_ui.databinding.ItemInfoEventBinding
import com.soma.common_ui.presentation.CommonVH
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.entity.InfoVO

class InfoEventVH(
    private val binding: ItemInfoEventBinding,
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as InfoVO
        binding.viewObject = viewObject
    }
}