package com.soma.lof.community

import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bookmark")
fun AppCompatImageView.bindImage(isChecked: Boolean) {
    val drawableInt = if (isChecked) R.drawable.ic_post_bookmark_checked else R.drawable.ic_post_bookmark
    this.setImageDrawable(AppCompatResources.getDrawable(context, drawableInt))

}