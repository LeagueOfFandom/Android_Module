package com.soma.lof.di

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.soma.lof.MainActivity
import com.soma.lof.login.ui.LoginActivity
import com.soma.lof.select_team.ui.SelectTeamActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

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

    @Singleton
    @Provides
    fun provideGso(): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
    }

    @Singleton
    @Provides
    fun provideGoogleSignInClient(gso: GoogleSignInOptions, @ApplicationContext context: Context): GoogleSignInClient {
        return GoogleSignIn.getClient(context, gso)
    }

    @Singleton
    @Provides
    fun provideGsa(@ApplicationContext context: Context): GoogleSignInAccount? {
        return GoogleSignIn.getLastSignedInAccount(context)
    }
}