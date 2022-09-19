package com.soma.lof.select_team.di

import com.soma.lof.common.api.TeamService
import com.soma.lof.select_team.repository.SelectTeamFakeRepository
import com.soma.lof.select_team.repository.SelectTeamFakeRepositoryImpl
import com.soma.lof.select_team.repository.SelectTeamRepository
import com.soma.lof.select_team.repository.SelectTeamRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SelectTeamModule {

    @Provides
    @Singleton
    fun provideSelectTeamRepository(
        teamService: TeamService
    ) : SelectTeamRepository {
        return SelectTeamRepositoryImpl(teamService)
    }

    @Provides
    @Singleton
    fun provideFakeSelectTeamRepository() : SelectTeamFakeRepository {
        return SelectTeamFakeRepositoryImpl()
    }
}