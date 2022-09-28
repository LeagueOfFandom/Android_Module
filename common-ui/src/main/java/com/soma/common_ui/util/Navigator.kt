package com.soma.common_ui.util

import androidx.navigation.NavController
import com.soma.common_ui.MainNavGraphDirections

class Navigator {
    lateinit var navController: NavController

    fun navigateToFlow(navigationFlow: NavigationFlow) = when (navigationFlow) {
        is NavigationFlow.HomeFlow -> navController.navigate(MainNavGraphDirections.actionGlobalHomeFlow())
        is NavigationFlow.MatchFlow -> navController.navigate(MainNavGraphDirections.actionGlobalMatchFlow())
        is NavigationFlow.CommunityFlow -> navController.navigate(MainNavGraphDirections.actionGlobalCommunityFlow())
        is NavigationFlow.InfoFlow -> navController.navigate(MainNavGraphDirections.actionGlobalInfoFlow())
        is NavigationFlow.SettingFlow -> navController.navigate(MainNavGraphDirections.actionGlobalSettingFlow())
    }
}