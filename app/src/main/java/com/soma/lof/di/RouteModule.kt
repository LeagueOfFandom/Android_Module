package com.soma.lof.di

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.soma.lof.FeatureHomeRoute
import com.soma.lof.MainActivity
import com.soma.lof.R
import com.soma.lof.common.route.FeatureHomeRouteContract
import com.soma.lof.common.route.FeatureLoginRouteContract
import com.soma.lof.common.route.FeatureMatchInfoRouteContract
import com.soma.lof.common.route.FeatureSelectTeamRouteContract
import com.soma.lof.login.FeatureLoginRoute
import com.soma.lof.match.route.FeatureMatchInfoContractImpl
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

    @Provides
    @Singleton
    fun providesFeatureMatchInfoRouteContract() : FeatureMatchInfoRouteContract =
        FeatureMatchInfoContractImpl()
}