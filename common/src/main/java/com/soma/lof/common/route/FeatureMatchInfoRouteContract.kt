package com.soma.lof.common.route

import android.app.Activity
import androidx.navigation.NavController

interface FeatureMatchInfoRouteContract {
    fun show(dataToPass: String, navController: NavController)
}