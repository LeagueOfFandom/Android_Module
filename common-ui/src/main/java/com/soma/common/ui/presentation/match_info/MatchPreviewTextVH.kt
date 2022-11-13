package com.soma.common.ui.presentation.match_info

import com.soma.common.ui.databinding.ItemMatchPreviewTextBinding
import com.soma.common.ui.presentation.CommonMatchInfoVH
import com.soma.common.ui.presentation.CommonVH
import com.soma.lof.core.model.dto.CommonItem
import com.soma.lof.core.model.entity.MatchPreviewCommonVO
import com.soma.lof.core.model.entity.MatchPreviewTextVO

class MatchPreviewTextVH(
    private val binding: ItemMatchPreviewTextBinding,
) : CommonMatchInfoVH(binding) {

    override fun bind(item: MatchPreviewCommonVO) {
        val data = item.viewStringObject as MatchPreviewTextVO
        binding.viewObject = data
    }

}