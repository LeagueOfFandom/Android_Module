package com.soma.common_ui.presentation.match_up

import com.soma.common_ui.databinding.ItemHomeMatchTitleLineBinding
import com.soma.common_ui.databinding.ItemMatchResultDateLineBinding
import com.soma.common_ui.presentation.CommonVH
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.entity.HomeMatchTitleVO
import com.soma.lof.core_model.entity.TextVO

class MatchResultDateLineVH (
    private val binding: ItemMatchResultDateLineBinding
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as TextVO
        binding.viewObject = viewObject
    }

    fun navigateSelect() {
//        Navigation.findNavController(itemView).deepLinkNavigateTo(DeepLinkDestination.Match.Result)
    }
}
