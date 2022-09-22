package com.soma.lof.match.route

import androidx.navigation.NavController
import com.soma.lof.common.route.FeatureMatchInfoRouteContract
import com.soma.lof.match.R
import javax.inject.Singleton

@Singleton
class FeatureMatchInfoContractImpl : FeatureMatchInfoRouteContract {

    override fun show(dataToPass: String, navController: NavController) {
        navController.navigate(R.id.nav_graph_match)
    }
}