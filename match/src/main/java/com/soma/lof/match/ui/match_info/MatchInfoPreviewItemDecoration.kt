package com.soma.lof.match.ui.match_info

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MatchInfoPreviewItemDecoration() : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = 20
        outRect.bottom = 20
    }
}