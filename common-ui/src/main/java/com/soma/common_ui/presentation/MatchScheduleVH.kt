package com.soma.common_ui.presentation

import androidx.navigation.Navigation
import com.soma.common_ui.databinding.ItemMatchScheduleBinding
import com.soma.common_ui.util.DeepLinkDestination
import com.soma.common_ui.util.deepLinkNavigateTo
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.entity.MatchVO

class MatchScheduleVH(
    private val binding: ItemMatchScheduleBinding
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as MatchVO
        binding.viewObject = viewObject
        binding.view = this@MatchScheduleVH
    }

    fun navigateMatchSchedule() {
        Navigation.findNavController(itemView).deepLinkNavigateTo(DeepLinkDestination.Match.Schedule)
    }

}