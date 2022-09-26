package com.soma.lof.common.ui

import androidx.navigation.Navigation.findNavController
import com.soma.lof.common.data.entity.CommonItem
import com.soma.lof.common.data.entity.MatchViewObject
import com.soma.lof.common.databinding.ItemMatchResultBinding
import com.soma.lof.common.util.DeepLinkDestination
import com.soma.lof.common.util.deepLinkNavigateTo

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