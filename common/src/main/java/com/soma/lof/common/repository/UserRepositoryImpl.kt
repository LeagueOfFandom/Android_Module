package com.soma.lof.common.repository

import com.soma.lof.common.api.UserService
import com.soma.lof.core_model.dto.CommonItemResponse
import com.soma.lof.core_model.dto.UserTokenRequest
import com.soma.lof.core_model.dto.UserTokenResponse
import com.soma.lof.core_model.entity.CommonViewObject
import com.soma.lof.foundation.exception.EmptyBodyException
import com.soma.lof.foundation.exception.NetworkFailureException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {

    override suspend fun postUserToken(userTokenRequest: UserTokenRequest): Flow<UserTokenResponse> =
        flow {
            val response = userService.postUserToken(userTokenRequest)
            if (response.isSuccessful) {
                val data: UserTokenResponse = response.body() ?: throw EmptyBodyException("[${response.code()}] - ${response.raw()}")
                emit(data)
            } else {
                throw NetworkFailureException("[${response.code()}] - ${response.raw()}")
            }
        }.catch { throw NetworkFailureException("Network Error") }

    override suspend fun postFcmToken(
        jwtToken: String,
        fcmToken: String,
    ): Response<UserTokenResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserInfo(jwtToken: String): List<CommonItemResponse> {
        val data = listOf(
            CommonItemResponse(
                "ONE_LINE_TEXT_VIEW",
                CommonViewObject(
                    text = "오늘"
                )
            ),
            CommonItemResponse(
                "INFO_EVENT_VIEW",
                CommonViewObject(
                    infoTitle = "T1 vs DX",
                    infoContent = "T1과 DX의 라이브 경기가 진행 중입니다. 확인하러 가볼까요?",
                    infoIsCheck = true,
                    infoTimeCompare = "1시간 전",
                    infoDateTime = "22.09.01. 17:30"
                )
            ),
            CommonItemResponse(
                "ONE_LINE_TEXT_VIEW",
                CommonViewObject(text="어제")
            ),
            CommonItemResponse(
                "INFO_LEAGUE_VIEW",
                CommonViewObject(
                    infoTitle= "DRX 2 : 1 KDF",
                    infoContent = "DRX가 KDF의 경기에서 2:1로 승리하였습니다!",
                    infoIsCheck = true,
                    infoTimeCompare = "하루 전",
                    infoDateTime = "22.08.30. 14:30"
                )
            ),
            CommonItemResponse(
                "INFO_HIGHLIGHT_VIEW",
                CommonViewObject(
                    infoTitle= "DRX vs T1 하이라이트 영상 업로드!",
                    infoContent = "9월 30일 18:00 경기의 하이라이트 영상이 업로드 되었습니다. 지금 확인해보세요!",
                    infoIsCheck = true,
                    infoTimeCompare = "1시간 전",
                    infoDateTime = "22.09.01. 17:30"
                )
            ),
            CommonItemResponse(
                "ONE_LINE_TEXT_VIEW",
                CommonViewObject(text="이번주")
            ),
            CommonItemResponse(
                "INFO_COMMENT_VIEW",
                CommonViewObject(
                    infoTitle= "'누구누구'님이 나의 글에 댓글을 남겼습니다",
                    infoContent = "와 너무 좋아요",
                    infoIsCheck = false,
                    infoTimeCompare = "1시간 전",
                    infoDateTime = "22.09.01. 17:30"
                )
            ),
            CommonItemResponse(
                "INFO_POST_LIKE_VIEW",
                CommonViewObject(
                    infoTitle = "'누구누구'님이 나의 게시글에 하트를 눌렀습니다.",
                    infoContent = "나의 게시글 - 와 너무 잘해",
                    infoIsCheck = false,
                    infoTimeCompare = "일주일 전",
                    infoDateTime = "22.09.01. 17:30"
                )
            ),
            CommonItemResponse(
                "INFO_POST_SUCCESS_VIEW",
                CommonViewObject(
                    infoTitle = "게시글을 성공적으로 업로드했습니다.",
                    infoContent = "나의 게시글 제목",
                    infoIsCheck = false,
                    infoTimeCompare = "1시간 전",
                    infoDateTime = "22.09.01. 17:30"
                )
            ),
        )
        return data
    }
}