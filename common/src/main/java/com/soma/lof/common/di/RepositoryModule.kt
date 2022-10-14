package com.soma.lof.common.di

import com.soma.lof.common.api.MatchService
import com.soma.lof.common.api.LeagueService
import com.soma.lof.common.api.UserService
import com.soma.lof.common.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesUserRepository(
        userService: UserService
    ) : UserRepository {
        return UserRepositoryImpl(userService)
    }

    @Provides
    @Singleton
    fun providesLeagueRepository(
        leagueService: LeagueService
    ) : LeagueRepository {
        return LeagueRepositoryImpl(leagueService)
    }

    @Provides
    @Singleton
    fun providesHomeRepository(matchService: MatchService) : HomeRepository {
        return HomeRepositoryImpl(matchService)
    }

    @Provides
    @Singleton
    fun providesMatchRepository(
        matchService: MatchService
    ) : MatchRepository {
        return MatchRepositoryImpl(matchService)
    }
}