package com.soma.lof.common.util

sealed class NavigationFlow {
    object HomeFlow : NavigationFlow()
    object MatchFlow : NavigationFlow()
    object CommunityFlow : NavigationFlow()
    object InfoFlow : NavigationFlow()
    object SettingFlow : NavigationFlow()
}