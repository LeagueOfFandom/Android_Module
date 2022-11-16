package com.soma.lof.core.data.di

import com.soma.lof.core.data.repository.*
import com.soma.lof.core.service.MatchService
import com.soma.lof.core.service.LeagueService
import com.soma.lof.core.service.UserService
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

    @Provides
    @Singleton
    fun providesCommunityRepository(
    ) : CommunityRepository {
        return CommunityRepositoryImpl()
    }
}