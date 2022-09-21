package com.soma.lof.home.repository

import com.soma.lof.common.data.entity.CommonItem
import com.soma.lof.common.data.entity.HighLightViewObject
import com.soma.lof.common.data.entity.MatchViewObject
import com.soma.lof.common.data.entity.TextArrowViewObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class HomeRepositoryImpl @Inject constructor() : HomeRepository {

    /*TEXT_ARROW_VIEW("TEXT_ARROW_VIEW"),
    MATCH_RESULT_VIEW("MATCH_RESULT_VIEW"),
    MATCH_SCHEDULE_VIEW("MATCH_SCHEDULE_VIEW"),
    USER_TEAM_VIEW("USER_TEAM_VIEW"),
    COMMUNITY_VIEW("COMMUNITY_VIEW"),
    HIGHLIGHT_VIEW("HIGHLIGHT_VIEW"),
    ERROR_VIEW("ERROR_VIEW")*/

    val data = listOf<CommonItem>(
        CommonItem(
            "MATCH_LIVE_VIEW",
            MatchViewObject(
                1L,
                "DK",
                "https://cdn.pixabay.com/photo/2018/05/13/16/57/dog-3397110__480.jpg",
                "T1",
                "https://cdn.pixabay.com/photo/2018/05/13/16/57/dog-3397110__480.jpg",
                "17:00",
                "LCK",
                false,
                0,
                0,
                isHide = true)
        ),
        CommonItem(
            "TEXT_ARROW_VIEW",
            TextArrowViewObject(
                "경기 일정"
            )
        ),
        CommonItem(
            "MATCH_SCHEDULE_VIEW",
            MatchViewObject(
                1L,
                "DK",
                "https://cdn.pixabay.com/photo/2018/05/13/16/57/dog-3397110__480.jpg",
                "T1",
                "https://cdn.pixabay.com/photo/2018/05/13/16/57/dog-3397110__480.jpg",
                "17:00",
                "LCK",
                false,
                0,
                0,
                isHide = true)
        ),
        CommonItem(
            "MATCH_RESULT_VIEW",
            MatchViewObject(
                1L,
                "DK",
                "https://cdn.pixabay.com/photo/2018/05/13/16/57/dog-3397110__480.jpg",
                "T1",
                "https://cdn.pixabay.com/photo/2018/05/13/16/57/dog-3397110__480.jpg",
                "17:00",
                "LCK",
                false,
                0,
                0,
                isHide = true)
        ),
        CommonItem(
            "TEXT_ARROW_VIEW",
            TextArrowViewObject(
                "My Team"
            )
        ),
        CommonItem(
            "TEXT_ARROW_VIEW",
            TextArrowViewObject(
                "실시간 인기글"
            )
        ),
        CommonItem(
            "TEXT_ARROW_VIEW",
            TextArrowViewObject(
                "하이라이트"
            )
        ),
        CommonItem(
            "HIGHLIGHT_VIEW",
            HighLightViewObject(
                listOf(
                    "fiY08uGY3dM",
                    "i7tT8BBy5zs",
                    "z1ai61tvmMo",
                    "gyD8bzTgRmM",
                    "V0N4C2Ax68Q"
                )
            )
        )
    )
    override suspend fun getHomeApi(): Flow<List<CommonItem>> = flow {
        emit(data)
    }
}