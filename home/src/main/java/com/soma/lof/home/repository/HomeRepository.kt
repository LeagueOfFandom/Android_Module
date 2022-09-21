package com.soma.lof.home.repository

import com.soma.lof.common.data.entity.CommonItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface HomeRepository {
    suspend fun getHomeApi() : Flow<List<CommonItem>>
}