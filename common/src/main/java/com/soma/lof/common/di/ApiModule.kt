package com.soma.lof.common.di

import com.soma.lof.common.api.MatchService
import com.soma.lof.common.api.TeamService
import com.soma.lof.common.api.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideTeamService(retrofit: Retrofit): TeamService {
        return retrofit.create(TeamService::class.java)
    }

    @Provides
    @Singleton
    fun provideMatchService(retrofit: Retrofit): MatchService {
        return retrofit.create(MatchService::class.java)
    }
}