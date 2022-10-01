package com.soma.lof.common.di

import com.soma.lof.common.repository.HomeRepository
import com.soma.lof.common.domain.HomeUseCase
import com.soma.lof.common.domain.MatchUseCase
import com.soma.lof.common.repository.MatchRepository
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
}