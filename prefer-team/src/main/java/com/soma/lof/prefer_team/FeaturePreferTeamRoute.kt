package com.soma.lof.prefer_team

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.soma.lof.common.route.FeaturePreferTeamRouteContract
import javax.inject.Singleton

@Singleton
class FeaturePreferTeamRoute : FeaturePreferTeamRouteContract {

    private var data: String? = null

    override fun setup(dataToPass: String) {
        data = dataToPass
    }

    override fun present(fromActivity: Activity, flags: IntArray) {
        val intent = Intent(fromActivity, PreferTeamActivity::class.java)
        for (flag in flags) {
            intent.addFlags(flag)
        }
        val bundle = Bundle()
        bundle.putString("data", data)
        intent.putExtras(bundle)
        fromActivity.startActivity(intent)
    }
}