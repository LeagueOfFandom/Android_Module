package com.soma.lof.core.data.repository

import com.soma.lof.core.model.dto.CommonItemResponse
import com.soma.lof.core.model.dto.MainPageResponse
import com.soma.lof.core.model.entity.CommonVO
import com.soma.lof.core.model.entity.TextVO
import com.soma.lof.core.network.exception.NetworkFailureException
import com.soma.lof.core.result.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * [CommonItemResponse] is for obtaining CommonList Data Form
 * CommonList is to apply ViewHolder and Data according to ViewType for Easy Recycling.
 */
class HomeRepositoryImpl @Inject constructor(
    private val matchService: com.soma.lof.core.service.MatchService
) : HomeRepository {

    override suspend fun getMainPage(
        jwtToken: String,
        onlyMyTeam: Boolean,
    ): Flow<UiState<MainPageResponse>> {
        return flow {
            val mainPageData = matchService.getMainPage(jwtToken, onlyMyTeam)
            emit(UiState.Success(mainPageData))
        }.catch {
            throw NetworkFailureException("Network Error ${it.message}")
        }
    }

    override suspend fun getFakeMainPage(
        jwtToken: String,
        onlyMyTeam: Boolean,
    ): Flow<UiState<MainPageResponse>> {
        return flow<UiState.Success<MainPageResponse>> {
            val fakeMainPageData = MainPageResponse(
                listOf("https://d654rq93y7j8z.cloudfront.net/lof_banner/1.jpg"),
                listOf(
                    CommonItemResponse(
                        "HOME_MATCH_TITLE_VIEW",
                        CommonVO(
                            text = "check"
                        )
                    ),
                    CommonItemResponse(
                        "LIVE_VIEW",
                        CommonVO(
                            matchId = 651268L,
                            homeName = "T1",
                            homeImg = "https://cdn.pandascore.co/images/team/image/126061/t_oscq04.png",
                            awayName = "DRX",
                            awayImg = "https://cdn.pandascore.co/images/team/image/126370/220px_dr_xlogo_square.png",
                            date = "2022-11-06",
                            time = "09:52:58",
                            league = "Playoffs",
                            isAlarm = false,
                            homeScore = 2,
                            awayScore = 3,
                            status = "finished",
                            videoLink = "https://www.naver.com"
                        )
                    ),
                    CommonItemResponse(
                        "MATCH_SCHEDULE_VIEW",
                        CommonVO(
                            matchId = 651268L,
                            homeName = "T1",
                            homeImg = "https://cdn.pandascore.co/images/team/image/126061/t_oscq04.png",
                            awayName = "DRX",
                            awayImg = "https://cdn.pandascore.co/images/team/image/126370/220px_dr_xlogo_square.png",
                            date = "2022-11-06",
                            time = "09:52:58",
                            league = "Playoffs",
                            isAlarm = false,
                            homeScore = 2,
                            awayScore = 3,
                            status = "finished",
                            videoLink = "https://www.naver.com"
                        )
                    )
                )
            )

            emit(UiState.Success(fakeMainPageData))
        }.catch {
            throw NetworkFailureException("Network Error ${it.message}")
        }
    }
}