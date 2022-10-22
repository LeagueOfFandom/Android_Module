package com.soma.lof.common.repository

import com.soma.lof.core_model.dto.CommonItemResponse
import com.soma.lof.core_model.dto.CreateUserRequest
import com.soma.lof.core_model.dto.CreateUserResponse
import com.soma.lof.foundation.result.Result
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserRepository {

    suspend fun createUser(createUserRequest: CreateUserRequest) : Flow<Result<CreateUserResponse>>

    suspend fun setUserNickName(jwtToken: String, nickname: String) : Flow<Result<String>>

    suspend fun getUserNickName(jwtToken: String) : Flow<Result<String>>

    suspend fun getUserAlarmSetting(jwtToken: String) : Flow<Result<Boolean>>

    suspend fun updateMatchAlarmSetting(jwtToken: String, alarm: Boolean) : Boolean

    // Info Fragment Dummy Data
    suspend fun getUserInfo(@Header("Authorization") jwtToken: String) : List<CommonItemResponse>
}