package com.soma.common.ui.presentation.match_up

import androidx.navigation.Navigation
import com.soma.common.ui.databinding.ItemMatchScheduleBinding
import com.soma.common.ui.presentation.CommonVH
import com.soma.common.ui.util.DeepLinkDestination
import com.soma.common.ui.util.deepLinkNavigateTo
import com.soma.lof.core.model.dto.CommonItem
import com.soma.lof.core.model.entity.MatchVO

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