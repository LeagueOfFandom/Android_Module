package com.soma.lof.common.repository

import com.soma.lof.common.api.MatchService
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.dto.CommonItemResponse
import com.soma.lof.core_model.dto.MatchInfoDummyResponse
import com.soma.lof.core_model.entity.*
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
class MatchRepositoryImpl @Inject constructor(
    private val matchService: MatchService,
) : MatchRepository {

    val data =
        listOf(
            CommonItem(
                "MATCH_RESULT_VIEW",
                MatchVO(
                    1L,
                    "DK",
                    "https://cdn.pixabay.com/photo/2018/05/13/16/57/dog-3397110__480.jpg",
                    "T1",
                    "https://cdn.pixabay.com/photo/2018/05/13/16/57/dog-3397110__480.jpg",
                    "2022년 8월 7일",
                    "17:00",
                    "LCK",
                    false,
                    0,
                    0,
                    isHide = true),
            ),
            CommonItem(
                "MATCH_SCHEDULE_VIEW",
                MatchVO(
                    1L,
                    "DK",
                    "https://cdn.pixabay.com/photo/2018/05/13/16/57/dog-3397110__480.jpg",
                    "T1",
                    "https://cdn.pixabay.com/photo/2018/05/13/16/57/dog-3397110__480.jpg",
                    "2022년 8월 7일",
                    "17:00",
                    "LCK",
                    false,
                    0,
                    0,
                    isHide = true),
            ),
            CommonItem(
                "MATCH_SCHEDULE_VIEW",
                MatchVO(
                    1L,
                    "DK",
                    "https://cdn.pixabay.com/photo/2018/05/13/16/57/dog-3397110__480.jpg",
                    "T1",
                    "https://cdn.pixabay.com/photo/2018/05/13/16/57/dog-3397110__480.jpg",
                    "2022년 8월 7일",
                    "17:00",
                    "LCK",
                    false,
                    0,
                    0,
                    isHide = true),
            ),
            CommonItem(
                "MATCH_SCHEDULE_VIEW",
                MatchVO(
                    1L,
                    "DK",
                    "https://cdn.pixabay.com/photo/2018/05/13/16/57/dog-3397110__480.jpg",
                    "T1",
                    "https://cdn.pixabay.com/photo/2018/05/13/16/57/dog-3397110__480.jpg",
                    "2022년 8월 7일",
                    "17:00",
                    "LCK",
                    false,
                    0,
                    0,
                    isHide = true),
            ),
        )

    val matchInfoDummy = MatchInfoDummyResponse(
        listOf(
            CommonItem(
                "MATCH_PREVIEW_TEXT_VIEW",
                MatchPreviewTextVO(
                    text = "KDA",
                    blueData = "14/5/40",
                    redData = "5/14/15"
                )
            ),
            CommonItem(
                "MATCH_PREVIEW_TEXT_VIEW",
                MatchPreviewTextVO(
                    text = "Text",
                    blueData = "1230",
                    redData = "555/15"
                )
            ),
            CommonItem(
                "MATCH_PREVIEW_IMAGE_VIEW",
                MatchPreviewImageVO(
                    "HERALDS",
                    listOf(
                        "https://media.istockphoto.com/vectors/dragon-icon-vector-illustration-vector-id877781616"
                    ),
                    listOf(
                        "https://media.istockphoto.com/vectors/dragon-icon-vector-illustration-vector-id877781616"
                    )
                )
            ),
        ),
        RosterObject(
            listOf(
                RosterData(
                    "top",
                    "H",
                    ""
                ),
                RosterData(
                    "jun",
                    "H",
                    ""
                ),
                RosterData(
                    "mid",
                    "H",
                    ""
                ),
                RosterData(
                    "adc",
                    "H",
                    ""
                ),
                RosterData(
                    "sup",
                    "H",
                    ""
                )
            ),
            listOf(
                RosterData(
                    "top",
                    "H",
                    ""
                ),
                RosterData(
                    "jun",
                    "H",
                    ""
                ),
                RosterData(
                    "mid",
                    "H",
                    ""
                ),
                RosterData(
                    "adc",
                    "H",
                    ""
                ),
                RosterData(
                    "sup",
                    "H",
                    ""
                )
            )
        ),
        MatchVO(
            0L,
            "DK",
            "",
            "T1",
            "",
            "2022년 8월 7일",
            "18:00",
            "LCK",
            true,
            1,
            0
        ),
        PredictionData(10, 20)
    )

    override suspend fun getMatchList(
        jwtString: String,
        date: String,
        onlyMyTeam: Boolean,
    ): Flow<Result<List<CommonItemResponse>>> {
        return flow {
            val matchList = matchService.getMatchList(jwtString, date, onlyMyTeam)
            emit(Result.Success(matchList))
        }.catch {
            throw NetworkFailureException("Network Error ${it.message}")
        }
    }

    override suspend fun getMatchInfoDataTest(): MatchInfoDummyResponse {
        return matchInfoDummy
    }
}