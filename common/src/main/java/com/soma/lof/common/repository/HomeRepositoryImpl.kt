package com.soma.lof.common.repository

import com.soma.lof.common.api.MatchService
import com.soma.lof.core_model.dto.CommonItemResponse
import com.soma.lof.foundation.exception.NetworkFailureException
import com.soma.lof.foundation.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * [CommonItemResponse] is for obtaining CommonList Data Form
 * CommonList is to apply ViewHolder and Data according to ViewType for Easy Recycling.
 */
class HomeRepositoryImpl @Inject constructor(
    private val matchService: MatchService
) : HomeRepository {

    override suspend fun getMainPage(
        jwtToken: String,
        onlyMyTeam: Boolean,
    ): Flow<Result<List<CommonItemResponse>>> {
        return flow {
            val mainPageData = matchService.getMainPage(jwtToken, onlyMyTeam)
            emit(Result.Success(mainPageData))
        }.catch {
            throw NetworkFailureException("Network Error ${it.message}")
        }
    }
}