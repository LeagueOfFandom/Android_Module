package com.soma.lof.common.domain

import com.soma.lof.common.repository.UserRepository
import com.soma.lof.core_model.dto.domain.SettingModel
import com.soma.lof.foundation.result.Result
import com.soma.lof.foundation.result.data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 *  [SettingModel] is Domain Layer Model
 */
class SettingUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend fun getUserSettingData(jwtToken: String): Flow<Result<SettingModel>> {
        return flow {
            emit(Result.Loading)
            val data = SettingModel()
            userRepository.getUserNickName(jwtToken).collectLatest {
                data.userNickName = it.data ?: ""
            }
            userRepository.getUserAlarmSetting(jwtToken).collectLatest {
                data.userAlarmSetting = it.data ?: true
            }
            emit(Result.Success(data))
        }
    }

    suspend fun postUserMatchAlarm(jwtToken: String, alarm: Boolean) : Boolean {
        return userRepository.updateMatchAlarmSetting(jwtToken, alarm)
    }
}