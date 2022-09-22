package com.soma.lof.common.ui

import androidx.navigation.Navigation.findNavController
import com.soma.lof.common.data.entity.CommonItem
import com.soma.lof.common.data.entity.MatchViewObject
import com.soma.lof.common.databinding.ItemMatchResultBinding
import com.soma.lof.common.route.FeatureMatchInfoRouteContract
import javax.inject.Inject

class MatchResultVH(private val binding: ItemMatchResultBinding) : CommonVH(binding) {

    @Inject lateinit var featureMatchInfoRouteContract: FeatureMatchInfoRouteContract

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as MatchViewObject
        binding.viewObject = viewObject
        binding.view = this@MatchResultVH
    }

    fun navigateMatchInfo() {
        featureMatchInfoRouteContract.show("", findNavController(itemView))
    }
}