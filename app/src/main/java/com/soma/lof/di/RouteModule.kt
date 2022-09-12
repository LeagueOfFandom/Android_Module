package com.soma.lof.di

import com.soma.lof.FeatureHomeRoute
import com.soma.lof.common.route.FeatureHomeRouteContract
import com.soma.lof.common.route.FeatureLoginRouteContract
import com.soma.lof.common.route.FeatureSelectTeamRouteContract
import com.soma.lof.login.FeatureLoginRoute
import com.soma.lof.select_team.route.FeatureSelectTeamRoute
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
    fun provideFeatureSelectTeamRoute() : FeatureSelectTeamRouteContract {
        return FeatureSelectTeamRoute()
    }

    @Provides
    @Singleton
    fun provideHomeRoute(): FeatureHomeRouteContract {
        return FeatureHomeRoute()
    }
}