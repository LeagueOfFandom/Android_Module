package com.soma.common.ui.presentation

import android.app.Activity
import com.soma.common.ui.databinding.ItemHomeMatchTitleLineBinding
import com.soma.common.ui.util.MainActivityUtil
import com.soma.lof.core.model.dto.CommonItem
import com.soma.lof.core.model.entity.TextVO

class HomeMatchTitleLineVH (
    private val binding: ItemHomeMatchTitleLineBinding,
    private val activity: Activity?
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as TextVO
        binding.apply {
            vh = this@HomeMatchTitleLineVH
            vo = viewObject
        }
    }

    fun navigateSelectTeam() {
        (activity as MainActivityUtil).startSelectTeamActivity()
    }

}
