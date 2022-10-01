package com.soma.lof.common.di

import com.soma.lof.common.api.MatchService
import com.soma.lof.common.api.TeamService
import com.soma.lof.common.api.UserService
import com.soma.lof.common.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        userService: UserService
    ) : UserRepository {
        return UserRepositoryImpl(userService)
    }

    @Provides
    @Singleton
    fun provideTeamRepository(
        teamService: TeamService
    ) : TeamRepository {
        return TeamRepositoryImpl(teamService)
    }

    @Provides
    @Singleton
    fun providesHomeRepository(userService: UserService) : HomeRepository {
        return HomeRepositoryImpl(userService)
    }

    @Provides
    @Singleton
    fun provideMatchRepository(
        matchService: MatchService
    ) : MatchRepository {
        return MatchRepositoryImpl(matchService)
    }
}