package com.soma.lof.core.network.di

import com.soma.lof.core.Dispatcher
import com.soma.lof.core.LofAppDispatchers
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