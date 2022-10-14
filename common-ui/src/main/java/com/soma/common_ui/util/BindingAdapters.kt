package com.soma.common_ui.util

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.soma.common_ui.presentation.CommonListAdapter2
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.dto.domain.SelectTeamModel
import com.soma.lof.foundation.result.Result
import com.soma.lof.foundation.result.data
import com.soma.lof.foundation.result.successOrNull
import me.relex.circleindicator.CircleIndicator3

@BindingAdapter("imageResource")
fun AppCompatImageView.setImageResource(resId: Int) {
    Glide.with(context)
        .load(resId)
        .into(this)
}

@BindingAdapter("itemDecoration")
fun RecyclerView.bindItemDecoration(itemDecoration: RecyclerView.ItemDecoration) {
    if (itemDecorationCount == 0) {
        addItemDecoration(itemDecoration)
    }
}

@BindingAdapter("toast")
fun View.bindToast(throwable: Throwable?) {
    throwable?.message?.let { errorMessage ->
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }
}

//@BindingAdapter("adapter")
//fun RecyclerView.bindAdapter(adapter: RecyclerView.Adapter<*>) {
//    this.adapter = adapter
//}

@BindingAdapter("image")
fun AppCompatImageView.bindImage(uri: String?) {
    if (uri != null) {
        Glide.with(context)
            .load(uri)
            .into(this)
    }
}

@BindingAdapter("commonItems")
fun RecyclerView.bindTeamItems(state: Result<List<CommonItem>>) {
    val boundAdapter = this.adapter
    if (boundAdapter is CommonListAdapter2 && state.successOrNull() != null) {
        boundAdapter.submitList(state.data)
    }
}


@BindingAdapter("show")
fun ProgressBar.bindShow(result: Result<*>?) {
    visibility = if (result is Result.Loading || result == null) View.VISIBLE else View.GONE
}

@BindingAdapter("viewpager")
fun CircleIndicator3.bindViewPager(viewPager: ViewPager2) {
    this.setViewPager(viewPager)
}

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("commonItems")
fun RecyclerView.bindCommonItems(state: Result<List<CommonItem>>) {
    val boundAdapter = this.adapter
    if (boundAdapter is CommonListAdapter2 && state.successOrNull() != null) {
        boundAdapter.submitList(state.data!!)
    }
}