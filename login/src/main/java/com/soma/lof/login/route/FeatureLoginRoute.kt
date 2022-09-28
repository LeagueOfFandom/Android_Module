package com.soma.lof.login.route

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.soma.lof.common.route.FeatureLoginRouteContract
import com.soma.lof.login.ui.LoginActivity
import javax.inject.Singleton

@Singleton
class FeatureLoginRoute : FeatureLoginRouteContract {

    private var data: String? = null

    override fun setup(dataToPass: String) {
        data = dataToPass
    }

    override fun present(fromActivity: Activity, flags: IntArray) {
        val intent = Intent(fromActivity, LoginActivity::class.java)

        for (flag in flags) {
            intent.addFlags(flag)
        }

        val bundle = Bundle()
        bundle.putString("data", data)
        intent.putExtras(bundle)
        fromActivity.startActivity(intent)
    }
}