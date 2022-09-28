package com.soma.lof.common.ui

import androidx.recyclerview.widget.DiffUtil
import com.soma.lof.common.data.entity.CommonItem

class CommonItemDiffUtilCallback : DiffUtil.ItemCallback<CommonItem>() {
    override fun areItemsTheSame(oldItem: CommonItem, newItem: CommonItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CommonItem, newItem: CommonItem): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

}