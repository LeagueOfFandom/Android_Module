package com.soma.lof.home.repository

import com.soma.lof.common.data.entity.CommonItem
import com.soma.lof.common.data.entity.CommonItemResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Header
import javax.inject.Singleton

@Singleton
interface HomeRepository {
    suspend fun getMainPage(jwtToken: String) : List<CommonItemResponse>
}