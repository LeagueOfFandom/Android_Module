package com.soma.lof.common.ui

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("adapter", "submitList", requireAll = true)
fun bindRecyclerView(view: RecyclerView, adapter: RecyclerView.Adapter<*>, submitList: List<Any>?) {
    view.adapter = adapter.apply {
        stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        (this as ListAdapter<Any, *>).submitList(submitList?.toMutableList())
    }
}

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}