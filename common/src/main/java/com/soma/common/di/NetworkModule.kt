package com.soma.common.di

import com.soma.common.repository.UserRepository
import com.soma.lof.foundation.NetworkUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    @Named("UserRepository")
    fun provideUserRepository(): UserRepository {
        return NetworkUtil.create(UserRepository::class.java)
    }
}