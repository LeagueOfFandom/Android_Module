package com.soma.lof.core.data.repository

import com.soma.lof.core.model.dto.CommonItemResponse
import com.soma.lof.core.model.dto.CreateUserRequest
import com.soma.lof.core.model.dto.CreateUserResponse
import com.soma.lof.core.model.dto.GetUserNicknameResponse
import com.soma.lof.core.result.UiState
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Header

interface UserRepository {

    suspend fun createUser(createUserRequest: CreateUserRequest) : Flow<UiState<CreateUserResponse>>

    suspend fun setUserNickName(jwtToken: String, nickname: String) : Flow<UiState<String>>

    suspend fun getUserNickName(jwtToken: String) : Flow<UiState<GetUserNicknameResponse>>

    suspend fun getUserAlarmSetting(jwtToken: String) : Flow<UiState<Boolean>>

    suspend fun updateMatchAlarmSetting(jwtToken: String, alarm: Boolean) : Boolean

    // Info Fragment Dummy Data
    suspend fun getUserInfo(jwtToken: String) : List<CommonItemResponse>

    suspend fun updateFCM(jwtToken: String, fcmToken: String)
}