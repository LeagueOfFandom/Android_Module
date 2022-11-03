package com.soma.lof.di

import com.soma.lof.MainActivity
import com.soma.lof.login.ui.LoginActivity
import com.soma.lof.select_team.ui.SelectTeamActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ActivityModule {

    @Singleton
    @Provides
    @Named("Main")
    fun provideMainActivity(): Class<*> = MainActivity::class.java

    @Singleton
    @Provides
    @Named("SelectTeam")
    fun provideSelectTeamActivity(): Class<*> = SelectTeamActivity::class.java

    @Singleton
    @Provides
    @Named("Login")
    fun provideLoginTeamActivity(): Class<*> = LoginActivity::class.java
}