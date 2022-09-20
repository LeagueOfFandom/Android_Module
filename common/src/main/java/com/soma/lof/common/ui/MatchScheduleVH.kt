package com.soma.lof.common.ui

import com.soma.lof.common.data.entity.CommonItem
import com.soma.lof.common.data.entity.MatchViewObject
import com.soma.lof.common.databinding.ItemMatchResultBinding
import com.soma.lof.common.databinding.ItemMatchScheduleBinding

class MatchScheduleVH(
    private val binding: ItemMatchScheduleBinding
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as MatchViewObject
        binding.viewObject = viewObject
    }

}