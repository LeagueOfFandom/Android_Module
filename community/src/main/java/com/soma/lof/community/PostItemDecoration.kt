package com.soma.lof.community

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PostItemDecoration() : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.top = 20
    }

}