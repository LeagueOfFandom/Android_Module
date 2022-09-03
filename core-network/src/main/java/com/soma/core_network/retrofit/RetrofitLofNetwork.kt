package com.soma.core_network.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

object RetrofitLofNetwork {
    //    private const val BASE_URL = "https://leagueoffandom.site" //실 서버
    private const val BASE_URL = "http://43.200.9.89" // 개발 서버

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .build()

    private val gson: Gson = GsonBuilder().setLenient().create()

    private val networkApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()
        .create(RetrofitLofNetworkApi::class.java)

    override fun getTeamRankList(league: String, season: String, year: String): TeamRankResponse =
        networkApi.getTeamRankList(league, season, year)
}