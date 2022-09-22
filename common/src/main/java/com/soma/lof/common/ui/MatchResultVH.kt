package com.soma.lof.common.ui

import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
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
        val request = NavDeepLinkRequest.Builder
            .fromUri("android-app://example.google.app/match_info_fragment".toUri())
            .build()
        findNavController(itemView).navigate(request)
        // featureMatchInfoRouteContract.show("", findNavController(itemView))
    }
}