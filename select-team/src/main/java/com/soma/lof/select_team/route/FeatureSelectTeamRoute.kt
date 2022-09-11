package com.soma.lof.select_team.route

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.soma.lof.common.route.FeatureSelectTeamRouteContract
import com.soma.lof.select_team.ui.SelectTeamActivity
import javax.inject.Singleton

@Singleton
class FeatureSelectTeamRoute : FeatureSelectTeamRouteContract {

    private var data: String? = null

    override fun setup(dataToPass: String) {
        data = dataToPass
    }

    override fun present(fromActivity: Activity, flags: IntArray) {
        val intent = Intent(fromActivity, SelectTeamActivity::class.java)
        for (flag in flags) {
            intent.addFlags(flag)
        }
        val bundle = Bundle()
        bundle.putString("data", data)
        intent.putExtras(bundle)
        fromActivity.startActivity(intent)
    }
}