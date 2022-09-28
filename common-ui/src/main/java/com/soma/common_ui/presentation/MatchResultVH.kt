package com.soma.common_ui.presentation

import androidx.navigation.Navigation.findNavController
import com.soma.common_ui.databinding.ItemMatchResultBinding
import com.soma.common_ui.util.DeepLinkDestination
import com.soma.common_ui.util.deepLinkNavigateTo
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.entity.MatchViewObject

class MatchResultVH(private val binding: ItemMatchResultBinding) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as MatchViewObject
        binding.viewObject = viewObject
        binding.view = this@MatchResultVH
    }

    fun navigateMatchResult() {
        findNavController(itemView).deepLinkNavigateTo(DeepLinkDestination.Match.Result)
    }
}