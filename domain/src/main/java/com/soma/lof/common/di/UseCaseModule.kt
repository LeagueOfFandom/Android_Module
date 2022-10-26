package com.soma.lof.common.di

import com.soma.lof.common.usecase.HomeUseCase
import com.soma.lof.common.usecase.InfoUseCase
import com.soma.lof.common.usecase.MatchUseCase
import com.soma.lof.common.usecase.SelectTeamUseCase
import com.soma.lof.core.data.repository.HomeRepository
import com.soma.lof.core.data.repository.LeagueRepository
import com.soma.lof.core.data.repository.MatchRepository
import com.soma.lof.core.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesHomeUseCase(homeRepository: HomeRepository) : HomeUseCase {
        return HomeUseCase(homeRepository)
    }

    @Provides
    @Singleton
    fun providesMatchUseCase(matchRepository: MatchRepository) : MatchUseCase {
        return MatchUseCase(matchRepository)
    }

    @Provides
    @Singleton
    fun providesInfoUseCase(userRepository: UserRepository) : InfoUseCase {
        return InfoUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun providesSelectTeamUseCase(leagueRepository: LeagueRepository) : SelectTeamUseCase {
        return SelectTeamUseCase(leagueRepository)
    }
}