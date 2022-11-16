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
                listOf("https://event.img.afreecatv.com/esports/news/fomos/2021/08/29/2965612a5d855f259.jpg"),
                listOf(
                    CommonItemResponse(
                        "LIVE_VIEW",
                        CommonVO(
                            matchId = 651268L,
                            homeName = "BFO",
                            homeImg = "https://cdn.pandascore.co/images/team/image/126073/220px_saigon_buffalologo_square.png",
                            awayName = "IST",
                            awayImg = "https://cdn.pandascore.co/images/team/image/126066/220px_istanbul_wildcatslogo_square.png",
                            date = "2022-11-06",
                            time = "09:52:58",
                            league = "Playoffs",
                            isAlarm = false,
                            homeScore = 1,
                            awayScore = 2,
                            status = "finished",
                            videoLink = "https://www.naver.com"
                        )
                    ),

                    CommonItemResponse(
                        "HOME_MATCH_TITLE_VIEW",
                        CommonVO(
                            text = "My Team 경기"
                        )
                    ),

                    CommonItemResponse(
                        "MATCH_RESULT_DATE_LINE",
                        CommonVO(
                            text = "2022년 11월 3일"
                        )
                    ),
                    CommonItemResponse(
                        "MATCH_RESULT_VIEW",
                        CommonVO(
                            matchId = 651268L,
                            homeName = "DRX",
                            homeImg = "https://cdn.pandascore.co/images/team/image/126370/220px_dr_xlogo_square.png",
                            awayName = "LION",
                            awayImg = "https://cdn.pandascore.co/images/team/image/126536/220px_mad_lions_e.c.__lec_team_logo_profile.png",
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
                        "MATCH_TODAY_DATE_LINE",
                        CommonVO(
                            text = "2022년 11월 4일"
                        )
                    ),
                    CommonItemResponse(
                        "MATCH_RESULT_VIEW",
                        CommonVO(
                            matchId = 651268L,
                            homeName = "DWG",
                            homeImg = "https://cdn.pandascore.co/images/team/image/128409/dwg_ki_alogo_square.png",
                            awayName = "LOUD",
                            awayImg = "https://cdn.pandascore.co/images/team/image/128313/lou_dlogo_square.png",
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
                            homeName = "TBC",
                            homeImg = "https://cdn.pandascore.co/images/team/image/130355/ctbc_flying_oysterlogo_square.png",
                            awayName = "BYG",
                            awayImg = "https://cdn.pandascore.co/images/team/image/128546/beyond_gaminglogo_square.png",
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
                        "MATCH_SCHEDULE_DATE_LINE",
                        CommonVO(
                            text = "2022년 11월 5일"
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
                    ),

                )
            )

            emit(UiState.Success(fakeMainPageData))
        }.catch {
            throw NetworkFailureException("Network Error ${it.message}")
        }
    }
}