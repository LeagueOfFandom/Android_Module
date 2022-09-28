package com.soma.lof.home.di

import com.soma.lof.common.api.UserService
import com.soma.lof.home.repository.HomeRepository
import com.soma.lof.home.repository.HomeRepositoryImpl
import com.soma.lof.home.usecase.HomeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeUseCaseModule {

    @Provides
    @Singleton
    fun providesHomeUseCase(homeRepository: HomeRepository) : HomeUseCase {
        return HomeUseCase(homeRepository)
    }
}