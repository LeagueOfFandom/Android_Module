package com.soma.lof.common.ui

import com.soma.lof.common.data.entity.CommonItem
import com.soma.lof.common.data.entity.CommunityViewObject
import com.soma.lof.common.data.entity.HighLightViewObject
import com.soma.lof.common.databinding.ItemCommunityBinding

class CommunityVH(
    private val binding: ItemCommunityBinding,
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as CommunityViewObject
        binding.viewObject = viewObject
    }
}