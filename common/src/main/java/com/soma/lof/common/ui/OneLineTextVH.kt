package com.soma.lof.common.ui

import com.soma.lof.common.data.entity.CommonItem
import com.soma.lof.common.data.entity.CommunityViewObject
import com.soma.lof.common.data.entity.HighLightViewObject
import com.soma.lof.common.data.entity.OneLineTextViewObject
import com.soma.lof.common.databinding.ItemCommunityBinding
import com.soma.lof.common.databinding.ItemOneLineTextBinding

class OneLineTextVH(
    private val binding: ItemOneLineTextBinding,
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as OneLineTextViewObject
        binding.viewObject = viewObject
    }
}