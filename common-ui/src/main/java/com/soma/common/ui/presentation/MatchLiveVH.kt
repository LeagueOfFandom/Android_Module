package com.soma.common.ui.presentation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.soma.common.ui.databinding.ItemMatchLiveBinding
import com.soma.lof.core.model.dto.CommonItem
import com.soma.lof.core.model.entity.MatchVO

class MatchLiveVH(
    private val binding: ItemMatchLiveBinding,
    private val activity: Activity?
) : CommonVH(binding) {

    override fun bind(item: CommonItem) {
        val viewObject = item.viewObject as MatchVO
        binding.viewObject = viewObject

        binding.matchLiveLayout.setOnClickListener {
            if (viewObject.videoLink != "") {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(viewObject.videoLink)
                }
                activity?.startActivity(intent)
            } else {
                Toast.makeText(activity, "등록된 live 링크가 없습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }


}