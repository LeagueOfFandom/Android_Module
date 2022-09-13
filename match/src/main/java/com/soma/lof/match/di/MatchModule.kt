package com.soma.lof.match.di

import com.soma.lof.match.repository.FakeMatchRepository
import com.soma.lof.match.repository.FakeMatchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MatchModule {

    @Provides
    @Singleton
    fun provideFakeMatchRepository() : FakeMatchRepository{
        return FakeMatchRepositoryImpl()
    }
}