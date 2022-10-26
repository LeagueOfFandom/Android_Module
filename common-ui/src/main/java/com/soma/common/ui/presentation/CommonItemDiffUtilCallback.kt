package com.soma.common.ui.presentation

import androidx.recyclerview.widget.DiffUtil
import com.soma.lof.core.model.dto.CommonItem

class CommonItemDiffUtilCallback : DiffUtil.ItemCallback<CommonItem>() {
    override fun areItemsTheSame(oldItem: CommonItem, newItem: CommonItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CommonItem, newItem: CommonItem): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

}