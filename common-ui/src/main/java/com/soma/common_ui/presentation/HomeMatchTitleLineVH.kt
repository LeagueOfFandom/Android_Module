package com.soma.common_ui.presentation

import android.widget.Toast
import com.soma.common_ui.databinding.ItemHomeMatchTitleLineBinding
import com.soma.common_ui.route.FeatureHomeRouteContract
import com.soma.common_ui.route.FeatureSelectTeamRouteContract
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.entity.TextVO
import javax.inject.Inject

class HomeMatchTitleLineVH (
    private val binding: ItemHomeMatchTitleLineBinding
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as TextVO
        binding.apply {
            vh = this@HomeMatchTitleLineVH
            vo = viewObject
        }
    }

}
