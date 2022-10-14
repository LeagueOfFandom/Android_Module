package com.soma.lof.home.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.soma.common_ui.presentation.CommonListAdapter2
import com.soma.lof.core_model.dto.domain.HomeModel
import com.soma.lof.foundation.result.Result
import com.soma.lof.foundation.result.data

@BindingAdapter("homeCommonItems")
fun RecyclerView.bindHomeCommonItems(state: Result<HomeModel>) {
    val boundAdapter = this.adapter
    if (boundAdapter is CommonListAdapter2) {
        boundAdapter.submitList(state.data?.commonItemList)
    }
}