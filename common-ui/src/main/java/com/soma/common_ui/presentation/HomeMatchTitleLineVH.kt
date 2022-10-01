package com.soma.common_ui.presentation

import android.widget.Toast
import androidx.navigation.Navigation
import com.soma.common_ui.databinding.ItemHomeMatchTitleLineBinding
import com.soma.common_ui.util.DeepLinkDestination
import com.soma.common_ui.util.deepLinkNavigateTo
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.entity.HomeMatchTitleViewObject

class HomeMatchTitleLineVH (
    private val binding: ItemHomeMatchTitleLineBinding
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as HomeMatchTitleViewObject
        binding.viewObject = viewObject
    }

    fun navigateSelect() {
//        Navigation.findNavController(itemView).deepLinkNavigateTo(DeepLinkDestination.Match.Result)
    }
}
