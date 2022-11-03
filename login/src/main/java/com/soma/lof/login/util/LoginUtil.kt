package com.soma.lof.login.util

import android.app.Activity
import android.content.Intent

object LoginUtil {
    fun startSelectTeamActivity(activity: Activity, selectTeamActivityClass: Class<*>) {
        val intent = Intent(activity, selectTeamActivityClass).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        activity.startActivity(intent)
    }

    fun startMainActivity(activity: Activity, homeActivityClass: Class<*>) {
        val intent = Intent(activity, homeActivityClass).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        activity.startActivity(intent)
    }

    fun startLoginActivity(activity: Activity, loginActivityClass: Class<*>) {
        val intent = Intent(activity, loginActivityClass).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        activity.startActivity(intent)
    }
}