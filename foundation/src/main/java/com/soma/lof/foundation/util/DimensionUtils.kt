package com.soma.lof.foundation.util

import android.content.Context
import android.util.TypedValue

fun Context.dpToPx(dp: Float): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()