package com.soma.lof.common.di

import com.soma.lof.common.repository.TeamRepository
import com.soma.lof.common.repository.TeamRepositoryImpl
import com.soma.lof.common.repository.UserRepository
import com.soma.lof.common.repository.UserRepositoryImpl
import com.soma.lof.foundation.api.TeamService
import com.soma.lof.foundation.api.UserService
import dagger.Binds
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
        @Named("UserService") userService: UserService
    ) : UserRepository {
        return UserRepositoryImpl(userService)
    }

    @Provides
    @Singleton
    fun provideTeamRepository(
        @Named("TeamService") teamService: TeamService
    ) : TeamRepository {
        return TeamRepositoryImpl(teamService)
    }
}