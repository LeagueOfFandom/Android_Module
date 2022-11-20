package com.soma.lof.core.data.repository

import com.soma.lof.core.model.dto.CommonItemResponse
import com.soma.lof.core.model.dto.CreateUserRequest
import com.soma.lof.core.model.dto.CreateUserResponse
import com.soma.lof.core.model.dto.UserNicknameResponse
import com.soma.lof.core.model.entity.CommonVO
import com.soma.lof.core.model.entity.NewUserResponse
import com.soma.lof.core.network.exception.NetworkFailureException
import com.soma.lof.core.result.UiState
import com.soma.lof.core.service.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {

    override suspend fun isNewUser(email: String): Flow<UiState<NewUserResponse>> {
        return flow {
            emit(UiState.Success(userService.isNewUser(email)))
        }.catch {
            throw NetworkFailureException("Network Error ${it.message}")
        }
    }

    override suspend fun createUser(createUserRequest: CreateUserRequest): Flow<UiState<CreateUserResponse>> {
        return flow {
            val data = userService.createUser(createUserRequest)
            emit(UiState.Success(data))
        }.catch {
            throw NetworkFailureException("Network Error ${it.message}")
        }
    }

    override suspend fun setUserNickName(jwtToken: String, nickname: String): Flow<UiState<UserNicknameResponse>> {
        return flow<UiState<UserNicknameResponse>> {
            emit(UiState.Success(userService.setUserNickName(jwtToken, nickname)))
        }.catch {
            throw NetworkFailureException("Network Error ${it.message}")
        }
    }

    override suspend fun getUserNickName(jwtToken: String): Flow<UiState<UserNicknameResponse>> {
        return flow<UiState<UserNicknameResponse>> {
            emit(UiState.Success(userService.getUserNickName(jwtToken)))
        }.catch {
            throw NetworkFailureException("Network Error ${it.message}")
        }
    }

    override suspend fun getUserAlarmSetting(jwtToken: String): Flow<UiState<Boolean>> {
        return flow<UiState<Boolean>> {
            emit(UiState.Success(userService.getUserAlarmSetting(jwtToken)))
        }.catch {
            throw NetworkFailureException("Network Error ${it.message}")
        }
    }

    override suspend fun updateMatchAlarmSetting(jwtToken: String, alarm: Boolean): Boolean {
        return userService.updateUserAlarmSetting(jwtToken, alarm)
    }

    override suspend fun getUserInfo(jwtToken: String): List<CommonItemResponse> {
        val data = listOf(
            CommonItemResponse(
                "ONE_LINE_TEXT_VIEW",
                CommonVO(
                    text = "오늘"
                )
            ),
            CommonItemResponse(
                "INFO_EVENT_VIEW",
                CommonVO(
                    infoTitle = "T1 vs DX",
                    infoContent = "T1과 DX의 라이브 경기가 진행 중입니다. 확인하러 가볼까요?",
                    infoIsCheck = true,
                    infoTimeCompare = "1시간 전",
                    infoDateTime = "22.09.01. 17:30"
                )
            ),
            CommonItemResponse(
                "ONE_LINE_TEXT_VIEW",
                CommonVO(text="어제")
            ),
            CommonItemResponse(
                "INFO_LEAGUE_VIEW",
                CommonVO(
                    infoTitle= "DRX 2 : 1 KDF",
                    infoContent = "DRX가 KDF의 경기에서 2:1로 승리하였습니다!",
                    infoIsCheck = true,
                    infoTimeCompare = "하루 전",
                    infoDateTime = "22.08.30. 14:30"
                )
            ),
            CommonItemResponse(
                "INFO_HIGHLIGHT_VIEW",
                CommonVO(
                    infoTitle= "DRX vs T1 하이라이트 영상 업로드!",
                    infoContent = "9월 30일 18:00 경기의 하이라이트 영상이 업로드 되었습니다. 지금 확인해보세요!",
                    infoIsCheck = true,
                    infoTimeCompare = "1시간 전",
                    infoDateTime = "22.09.01. 17:30"
                )
            ),
            CommonItemResponse(
                "ONE_LINE_TEXT_VIEW",
                CommonVO(text="이번주")
            ),
            CommonItemResponse(
                "INFO_COMMENT_VIEW",
                CommonVO(
                    infoTitle= "'누구누구'님이 나의 글에 댓글을 남겼습니다",
                    infoContent = "와 너무 좋아요",
                    infoIsCheck = false,
                    infoTimeCompare = "1시간 전",
                    infoDateTime = "22.09.01. 17:30"
                )
            ),
            CommonItemResponse(
                "INFO_POST_LIKE_VIEW",
                CommonVO(
                    infoTitle = "'누구누구'님이 나의 게시글에 하트를 눌렀습니다.",
                    infoContent = "나의 게시글 - 와 너무 잘해",
                    infoIsCheck = false,
                    infoTimeCompare = "일주일 전",
                    infoDateTime = "22.09.01. 17:30"
                )
            ),
            CommonItemResponse(
                "INFO_POST_SUCCESS_VIEW",
                CommonVO(
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

    override suspend fun updateFCM(jwtToken: String, fcmToken: String) {
        userService.updateFCM(jwtToken, fcmToken)
    }
}