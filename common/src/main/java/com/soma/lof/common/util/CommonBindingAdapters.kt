package com.soma.lof.common.util

import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

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

@BindingAdapter("adapter")
fun RecyclerView.bindAdapter(adapter: RecyclerView.Adapter<*>) {
    this.adapter = adapter
}

@BindingAdapter("image")
fun AppCompatImageView.bindImage(uri: String?) {
    if (uri != null) {
        Glide.with(context)
            .load(uri)
            .into(this)
    }
}