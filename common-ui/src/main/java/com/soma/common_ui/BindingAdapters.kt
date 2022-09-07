package com.soma.common_ui

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageResource")
fun AppCompatImageView.setImageResource(resId: Int) {
    Glide.with(context)
        .load(resId)
        .into(this)
}