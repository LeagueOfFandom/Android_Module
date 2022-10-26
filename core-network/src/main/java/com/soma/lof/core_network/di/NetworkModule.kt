package com.soma.lof.core_network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
//        private val BASE_URL = "https://leagueoffandom.site" //실 서버
    private val BASE_URL = "http://43.200.9.89" // 개발 서버

    @Provides
    @Singleton
    fun provideRequestHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): com.soma.lof.core_network.service.UserService {
        return retrofit.create(com.soma.lof.core_network.service.UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideLeagueService(retrofit: Retrofit): com.soma.lof.core_network.service.LeagueService {
        return retrofit.create(com.soma.lof.core_network.service.LeagueService::class.java)
    }

    @Provides
    @Singleton
    fun provideMatchService(retrofit: Retrofit): com.soma.lof.core_network.service.MatchService {
        return retrofit.create(com.soma.lof.core_network.service.MatchService::class.java)
    }
}