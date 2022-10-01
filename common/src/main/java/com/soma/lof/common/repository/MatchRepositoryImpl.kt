package com.soma.lof.common.repository

import com.soma.lof.common.api.MatchService
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.dto.CommonItemResponse
import com.soma.lof.core_model.entity.MatchViewObject
import com.soma.lof.foundation.exception.EmptyBodyException
import com.soma.lof.foundation.exception.NetworkFailureException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MatchRepositoryImpl @Inject constructor(
    private val matchService: MatchService,
) : MatchRepository {

    val data =
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
        )


    override suspend fun getMatchList(
        jwtString: String,
        date: String,
        isAll: Boolean,
    ): List<CommonItemResponse> {
        val response = matchService.getMatchList(jwtString, date, isAll)

        if (response.isSuccessful) {
            return response.body()
                ?: throw EmptyBodyException("[${response.code()}] - ${response.raw()}")
        } else {
            throw NetworkFailureException("[${response.code()}] - ${response.raw()}")
        }
    }


}