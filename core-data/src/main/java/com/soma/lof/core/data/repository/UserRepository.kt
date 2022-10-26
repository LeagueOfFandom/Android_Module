package com.soma.lof.core.data.repository

import com.soma.lof.core_model.dto.CommonItemResponse
import com.soma.lof.core_model.dto.CreateUserRequest
import com.soma.lof.core_model.dto.CreateUserResponse
import com.soma.lof.core_network.result.UiState
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Header

interface UserRepository {

    suspend fun createUser(createUserRequest: CreateUserRequest) : Flow<UiState<CreateUserResponse>>

    suspend fun setUserNickName(jwtToken: String, nickname: String) : Flow<UiState<String>>

    suspend fun getUserNickName(jwtToken: String) : Flow<UiState<String>>

    suspend fun getUserAlarmSetting(jwtToken: String) : Flow<UiState<Boolean>>

    suspend fun updateMatchAlarmSetting(jwtToken: String, alarm: Boolean) : Boolean

    // Info Fragment Dummy Data
    suspend fun getUserInfo(@Header("Authorization") jwtToken: String) : List<CommonItemResponse>
}