package com.soma.lof.di

import com.soma.lof.FeatureHomeRoute
import com.soma.lof.common.route.FeatureHomeRouteContract
import com.soma.lof.common.route.FeatureLoginRouteContract
import com.soma.lof.common.route.FeatureSelectTeamRouteContract
import com.soma.lof.login.route.FeatureLoginRoute
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
    fun providesFeatureLoginRoute() : FeatureLoginRouteContract {
        return FeatureLoginRoute()
    }

    @Provides
    @Singleton
    fun providesFeatureSelectTeamRoute() : FeatureSelectTeamRouteContract {
        return FeatureSelectTeamRoute()
    }

    @Provides
    @Singleton
    fun providesHomeRoute(): FeatureHomeRouteContract {
        return FeatureHomeRoute()
    }
}