package com.soma.common.ui.presentation

import com.soma.common.databinding.ItemHomeMatchTitleLineBinding
import com.soma.lof.core.model.dto.CommonItem
import com.soma.lof.core.model.entity.TextVO

class HomeMatchTitleLineVH (
    private val binding: ItemHomeMatchTitleLineBinding
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as TextVO
        binding.apply {
            vh = this@HomeMatchTitleLineVH
            vo = viewObject
        }
    }

}
