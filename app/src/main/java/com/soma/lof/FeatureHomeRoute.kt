package com.soma.lof

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.soma.common.ui.route.FeatureHomeRouteContract
import javax.inject.Singleton

@Singleton
class FeatureHomeRoute : FeatureHomeRouteContract {

    private var data: String? = null

    override fun setup(dataToPass: String) {
        data = dataToPass
    }

    override fun present(fromActivity: Activity, flags: IntArray) {
        val intent = Intent(fromActivity, MainActivity::class.java)

        for (flag in flags) {
            intent.addFlags(flag)
        }

        val bundle = Bundle()
        bundle.putString("data", data)
        intent.putExtras(bundle)
        fromActivity.startActivity(intent)
    }
}