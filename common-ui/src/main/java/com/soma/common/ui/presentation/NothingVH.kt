package com.soma.common.ui.presentation

import com.soma.common.ui.databinding.ItemNothingBinding
import com.soma.lof.core.model.dto.CommonItem

class NothingVH(
    private val binding: ItemNothingBinding,
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
    }
}