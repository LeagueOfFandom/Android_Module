package com.soma.lof.common.ui

import androidx.navigation.Navigation
import com.soma.lof.common.data.entity.CommonItem
import com.soma.lof.common.data.entity.MatchViewObject
import com.soma.lof.common.databinding.ItemMatchScheduleBinding
import com.soma.lof.common.util.DeepLinkDestination
import com.soma.lof.common.util.deepLinkNavigateTo

class MatchScheduleVH(
    private val binding: ItemMatchScheduleBinding
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as MatchViewObject
        binding.viewObject = viewObject
        binding.view = this@MatchScheduleVH
    }

    fun navigateMatchSchedule() {
        Navigation.findNavController(itemView).deepLinkNavigateTo(DeepLinkDestination.Match.Schedule)
    }

}