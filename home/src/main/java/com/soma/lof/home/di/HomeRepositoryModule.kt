package com.soma.lof.home.di

import com.soma.lof.home.repository.HomeRepository
import com.soma.lof.home.repository.HomeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeRepositoryModule {

    @Provides
    @Singleton
    fun providesHomeRepository() : HomeRepository{
        return HomeRepositoryImpl()
    }
}