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

    override suspend fun getFakeUserInfoList(jwtToken: String): List<CommonItemResponse> {
        val data = listOf(
            CommonItemResponse(
                "ONE_LINE_TEXT_VIEW",
                CommonVO(
                    text = "??????"
                )
            ),
            CommonItemResponse(
                "INFO_EVENT_VIEW",
                CommonVO(
                    infoTitle = "T1 vs DX",
                    infoContent = "T1??? DX??? ????????? ????????? ?????? ????????????. ???????????? ?????????????",
                    infoIsCheck = true,
                    infoTimeCompare = "1?????? ???",
                    infoDateTime = "22.09.01. 17:30"
                )
            ),
            CommonItemResponse(
                "ONE_LINE_TEXT_VIEW",
                CommonVO(text="??????")
            ),
            CommonItemResponse(
                "INFO_LEAGUE_VIEW",
                CommonVO(
                    infoTitle= "DRX 2 : 1 KDF",
                    infoContent = "DRX??? KDF??? ???????????? 2:1??? ?????????????????????!",
                    infoIsCheck = true,
                    infoTimeCompare = "?????? ???",
                    infoDateTime = "22.08.30. 14:30"
                )
            ),
            CommonItemResponse(
                "INFO_HIGHLIGHT_VIEW",
                CommonVO(
                    infoTitle= "DRX vs T1 ??????????????? ?????? ?????????!",
                    infoContent = "9??? 30??? 18:00 ????????? ??????????????? ????????? ????????? ???????????????. ?????? ??????????????????!",
                    infoIsCheck = true,
                    infoTimeCompare = "1?????? ???",
                    infoDateTime = "22.09.01. 17:30"
                )
            ),
            CommonItemResponse(
                "ONE_LINE_TEXT_VIEW",
                CommonVO(text="?????????")
            ),
            CommonItemResponse(
                "INFO_COMMENT_VIEW",
                CommonVO(
                    infoTitle= "'????????????'?????? ?????? ?????? ????????? ???????????????",
                    infoContent = "??? ?????? ?????????",
                    infoIsCheck = false,
                    infoTimeCompare = "1?????? ???",
                    infoDateTime = "22.09.01. 17:30"
                )
            ),
            CommonItemResponse(
                "INFO_POST_LIKE_VIEW",
                CommonVO(
                    infoTitle = "'????????????'?????? ?????? ???????????? ????????? ???????????????.",
                    infoContent = "?????? ????????? - ??? ?????? ??????",
                    infoIsCheck = false,
                    infoTimeCompare = "????????? ???",
                    infoDateTime = "22.09.01. 17:30"
                )
            ),
            CommonItemResponse(
                "INFO_POST_SUCCESS_VIEW",
                CommonVO(
                    infoTitle = "???????????? ??????????????? ?????????????????????.",
                    infoContent = "?????? ????????? ??????",
                    infoIsCheck = false,
                    infoTimeCompare = "1?????? ???",
                    infoDateTime = "22.09.01. 17:30"
                )
            ),
        )
        return data
    }

    override suspend fun getUserInfoList(jwtToken: String): Flow<UiState<List<CommonItemResponse>>> {
        return flow {
            emit(UiState.Success(userService.getUserInfoList(jwtToken)))
        }.catch {
            throw NetworkFailureException("Network Error ${it.message}")
        }
    }

    override suspend fun updateFCM(jwtToken: String, fcmToken: String) {
        userService.updateFCM(jwtToken, fcmToken)
    }
}