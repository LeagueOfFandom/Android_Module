package com.soma.lof.di

import com.soma.lof.common.route.FeatureLoginRouteContract
import com.soma.lof.common.route.FeaturePreferTeamRouteContract
import com.soma.lof.login.FeatureLoginRoute
import com.soma.lof.prefer_team.FeaturePreferTeamRoute
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RouteModule {

    @Provides
    @Singleton
    fun provideFeatureLoginRoute() : FeatureLoginRouteContract {
        return FeatureLoginRoute()
    }

    @Provides
    @Singleton
    fun provideFeatureTeamPreferRoute() : FeaturePreferTeamRouteContract {
        return FeaturePreferTeamRoute()
    }
}