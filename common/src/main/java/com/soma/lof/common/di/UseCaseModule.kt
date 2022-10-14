package com.soma.lof.common.di

import com.soma.lof.common.domain.HomeUseCase
import com.soma.lof.common.domain.InfoUseCase
import com.soma.lof.common.domain.MatchUseCase
import com.soma.lof.common.domain.SelectTeamUseCase
import com.soma.lof.common.repository.HomeRepository
import com.soma.lof.common.repository.LeagueRepository
import com.soma.lof.common.repository.MatchRepository
import com.soma.lof.common.repository.UserRepository
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