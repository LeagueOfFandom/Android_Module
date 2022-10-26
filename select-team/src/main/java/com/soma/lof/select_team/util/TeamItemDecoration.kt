package com.soma.lof.select_team.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.soma.common.ui.util.dpToPx

class TeamItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val spacePx = view.context.dpToPx(BETWEEN_SPACE) / 2
        if (parent.getChildAdapterPosition(view) % 2 != 0) {
            outRect.left = spacePx
        } else {
            outRect.right = spacePx
        }
    }

    companion object {
        private const val BETWEEN_SPACE = 15f
    }
}