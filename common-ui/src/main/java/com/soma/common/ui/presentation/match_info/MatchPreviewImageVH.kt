package com.soma.common.ui.presentation.match_info

import android.view.View
import com.soma.common.ui.databinding.ItemMatchPreviewImageBinding
import com.soma.common.ui.presentation.CommonMatchInfoVH
import com.soma.lof.core.model.entity.MatchPreviewCommonVO
import com.soma.lof.core.model.entity.MatchPreviewImageVO

class MatchPreviewImageVH(
    private val binding: ItemMatchPreviewImageBinding,
) : CommonMatchInfoVH(binding) {

    override fun bind(item: MatchPreviewCommonVO) {
        val data = item.viewImgObject as MatchPreviewImageVO
        binding.viewObject = data

        binding.itemMatchPreviewImageContent.text = data.text

        if (data.blueImgList.isEmpty()) {
            binding.itemMatchPreviewImageBlueSubstitute.visibility = View.VISIBLE
        } else {
            binding.itemMatchPreviewImageBlueSubstitute.visibility = View.GONE
            binding.itemMatchPreviewImageBlueTeam.apply {
                setHasFixedSize(true)
                adapter = MatchInfoPreviewImageAdapter(data.blueImgList)
            }
        }

        if (data.redImgList.isEmpty()) {
            binding.itemMatchPreviewImageRedSubstitute.visibility = View.VISIBLE
        } else {
            binding.itemMatchPreviewImageRedSubstitute.visibility = View.GONE
            binding.itemMatchPreviewImageRedTeam.apply {
                setHasFixedSize(true)
                adapter = MatchInfoPreviewImageAdapter(data.redImgList)
            }
        }
    }
}