package com.soma.common.ui.presentation.info

import com.soma.common.databinding.ItemInfoLeagueBinding
import com.soma.common.ui.presentation.CommonVH
import com.soma.lof.core.model.dto.CommonItem
import com.soma.lof.core.model.entity.InfoVO

class InfoLeagueVH(
    private val binding: ItemInfoLeagueBinding,
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as InfoVO
        binding.viewObject = viewObject
    }
}