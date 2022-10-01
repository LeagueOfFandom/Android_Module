package com.soma.common_ui.route

import android.app.Activity
import javax.inject.Singleton

@Singleton
interface FeatureHomeRouteContract {
    fun setup(dataToPass: String)
    fun present(fromActivity: Activity, flags: IntArray)
}
