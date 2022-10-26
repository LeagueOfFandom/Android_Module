package com.soma.lof.core_network.di

import com.soma.lof.core_network.Dispatcher
import com.soma.lof.core_network.LofAppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    @Dispatcher(LofAppDispatchers.IO)
    fun providesIODispatchers(): CoroutineDispatcher = Dispatchers.IO
}