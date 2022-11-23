package com.soma.lof.domain.usecase

import android.accounts.NetworkErrorException
import com.soma.lof.domain.util.CommonItemTranslator.toCommonItemList
import com.soma.lof.core.data.repository.UserRepository
import com.soma.lof.core.model.dto.CommonItem
import com.soma.lof.core.result.UiState
import com.soma.lof.core.result.data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InfoUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend fun getFakeUserInfo(jwtToken: String) =
        userRepository.getFakeUserInfoList(jwtToken).toCommonItemList()

    suspend fun getUserInfo(jwtToken: String) : Flow<UiState<List<CommonItem>>> {
        return flow {
            userRepository.getUserInfoList(jwtToken).collect {
                emit(UiState.Success(it.data?.toCommonItemList() ?: emptyList()))
            }
        }.catch {
            throw NetworkErrorException("Network Error ${it.message}")
        }
    }

}