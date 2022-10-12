package com.soma.common_ui.presentation

import android.view.View
import androidx.navigation.Navigation.findNavController
import com.soma.common_ui.databinding.ItemMatchResultBinding
import com.soma.common_ui.util.DeepLinkDestination
import com.soma.common_ui.util.deepLinkNavigateTo
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.entity.MatchVO

class MatchResultVH(private val binding: ItemMatchResultBinding) : CommonVH(binding) {

    private lateinit var viewObject: MatchVO

    override fun bind(item: CommonItem) {
        viewObject = item.viewObject as MatchVO
        binding.viewObject = viewObject
        binding.view = this@MatchResultVH
    }


    fun setHideOption() {
        viewObject.isHide = !viewObject.isHide
        binding.itemMatchResultScoreHide.visibility =
            if (viewObject.isHide) View.GONE else View.VISIBLE
    }

    fun navigateMatchResult() {
        findNavController(itemView).deepLinkNavigateTo(DeepLinkDestination.Match.Result)
    }
}