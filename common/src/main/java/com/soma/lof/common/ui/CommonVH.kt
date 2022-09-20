package com.soma.lof.common.ui

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.soma.lof.common.data.entity.CommonItem

abstract class CommonVH(
    binding: ViewDataBinding,
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: CommonItem)
}