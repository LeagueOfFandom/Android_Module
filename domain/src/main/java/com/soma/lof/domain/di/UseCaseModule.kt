package com.soma.lof.domain.di

import com.soma.lof.core.data.repository.*
import com.soma.lof.domain.usecase.*
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

    @Provides
    @Singleton
    fun providesCommunityUseCase(communityRepository: CommunityRepository) : CommunityUseCase {
        return CommunityUseCase(communityRepository)
    }
}