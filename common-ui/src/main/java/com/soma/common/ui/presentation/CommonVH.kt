package com.soma.common.ui.presentation

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.soma.lof.core.model.dto.CommonItem

abstract class CommonVH(
    binding: ViewDataBinding,
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: CommonItem)
}