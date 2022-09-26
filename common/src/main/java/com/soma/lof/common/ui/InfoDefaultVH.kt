package com.soma.lof.common.ui

import com.soma.lof.common.data.entity.CommonItem
import com.soma.lof.common.data.entity.CommunityViewObject
import com.soma.lof.common.data.entity.HighLightViewObject
import com.soma.lof.common.data.entity.InfoDefaultViewObject
import com.soma.lof.common.databinding.ItemCommunityBinding
import com.soma.lof.common.databinding.ItemInfoDefaultViewBinding

class InfoDefaultVH(
    private val binding: ItemInfoDefaultViewBinding,
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as InfoDefaultViewObject
        binding.viewObject = viewObject
    }
}