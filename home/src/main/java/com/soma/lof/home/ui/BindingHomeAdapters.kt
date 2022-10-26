package com.soma.lof.home.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.soma.common.ui.presentation.CommonListAdapter2
import com.soma.lof.domain.model.HomeModel
import com.soma.lof.core.result.UiState
import com.soma.lof.core.result.data

@BindingAdapter("homeCommonItems")
fun RecyclerView.bindHomeCommonItems(state: UiState<HomeModel>) {
    val boundAdapter = this.adapter
    if (boundAdapter is CommonListAdapter2) {
        boundAdapter.submitList(state.data?.commonItemList)
    }
}