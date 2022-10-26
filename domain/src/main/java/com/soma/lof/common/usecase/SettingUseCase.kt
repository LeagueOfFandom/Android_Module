package com.soma.lof.common.usecase

import com.soma.lof.core.data.repository.UserRepository
import com.soma.lof.core_model.dto.domain.SettingModel
import com.soma.lof.core_network.result.UiState
import com.soma.lof.core_network.result.data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 *  [SettingModel] is Domain Layer Model
 */
class SettingUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend fun getUserSettingData(jwtToken: String): Flow<UiState<SettingModel>> {
        return flow {
            emit(UiState.Loading)
            val data = SettingModel()
            userRepository.getUserNickName(jwtToken).collectLatest {
                data.userNickName = it.data ?: ""
            }
            userRepository.getUserAlarmSetting(jwtToken).collectLatest {
                data.userAlarmSetting = it.data ?: true
            }
            emit(UiState.Success(data))
        }
    }

    suspend fun postUserMatchAlarm(jwtToken: String, alarm: Boolean): Boolean {
        return userRepository.updateMatchAlarmSetting(jwtToken, alarm)
    }
}