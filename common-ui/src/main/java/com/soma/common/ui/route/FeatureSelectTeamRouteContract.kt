package com.soma.common.ui.route

import android.app.Activity
import javax.inject.Singleton

@Singleton
interface FeatureSelectTeamRouteContract {
    fun setup(dataToPass: String)
    fun present(fromActivity: Activity, flags: IntArray)
}
