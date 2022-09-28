package com.soma.lof.common.repository

import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.dto.MatchResponse
import com.soma.lof.core_model.entity.DateInfo
import com.soma.lof.core_model.entity.MatchViewObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MatchRepositoryImpl @Inject constructor(

) : MatchRepository {

    val data = MatchResponse(
        listOf(
            DateInfo("7월", "31일", "#000000"),
            DateInfo("8월", "1일", "#000000"),
            DateInfo("8월", "2일", "#426BFF"),
            DateInfo("8월", "3일", "#E2012D"),
            DateInfo("8월", "4일", "#000000"),
            DateInfo("8월", "5일", "#000000"),
            DateInfo("8월", "6일", "#000000"),
            DateInfo("8월", "7일", "#000000"),
            DateInfo("8월", "8일", "#000000"),
            DateInfo("8월", "9일", "#426BFF"),
            DateInfo("8월", "10일", "#E2012D"),
            DateInfo("8월", "11일", "#000000"),
            DateInfo("8월", "12일", "#000000"),
            DateInfo("8월", "13일", "#000000"),
        ),
        listOf(
            listOf(
                CommonItem(
                    "MATCH_RESULT_VIEW",
                    MatchViewObject(
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
                    MatchViewObject(
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
            ),
            listOf(),
            listOf(),
            listOf(),
            listOf(),
            listOf(),
            listOf(),
            listOf(),
            listOf(),
            listOf(),
            listOf(),
            listOf(),
            listOf(),
            listOf(),
        )
    )

    override fun getMatchData(): Flow<MatchResponse> {
        return flow {
            emit(data)
        }
    }
}