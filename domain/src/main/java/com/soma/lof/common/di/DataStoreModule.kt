package com.soma.lof.common.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.soma.lof.common.usecase.DataStoreUseCase
import com.soma.lof.common.util.DATASTORE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = DATASTORE_NAME
    )

    @Singleton
    @Provides
    fun provideDataStoreUseCase(@ApplicationContext context: Context) =
        DataStoreUseCase(context.dataStore)
}