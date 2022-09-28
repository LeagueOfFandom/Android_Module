package com.soma.lof.home.repository

import android.util.Log
import com.soma.lof.common.api.UserService
import com.soma.lof.common.data.entity.*
import com.soma.lof.foundation.exception.EmptyBodyException
import com.soma.lof.foundation.exception.NetworkFailureException
import javax.inject.Inject


class HomeRepositoryImpl @Inject constructor(
    private val userService: UserService,
) : HomeRepository {

    override suspend fun getMainPage(jwtToken: String): List<CommonItemResponse> {

        val response = userService.getMainPageTest(jwtToken)

        if (response.isSuccessful) {
            val data = response.body()
                ?: throw EmptyBodyException("[${response.code()}] - ${response.raw()}")
            Log.e("Home", "HomeRepositoryImpl: ${data}")
            return data
        } else {
            throw NetworkFailureException("[${response.code()}] - ${response.raw()}")
        }
    }

    val dummy: List<CommonItem> = listOf(
        CommonItem(
            "MATCH_LIVE_VIEW",
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
                "2022년 8월 7일",
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
                "2022년 8월 7일",
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
                "실시간 인기글"
            )
        ),
        CommonItem(
            "COMMUNITY_VIEW",
            CommunityViewObject(
                "박정근",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS8SqlL9vqiQx_2-5mA0MY0Dv-ECXBAjEYtNg&usqp=CAU",
                "2022년 9월 2일 22:05분",
                "커뮤니티 테스트 내용"
            )
        ),
        CommonItem(
            "COMMUNITY_VIEW",
            CommunityViewObject(
                "실시간 인기글",
                "https://file2.nocutnews.co.kr/newsroom/image/2020/04/22/20200422143312218214_0_800_800.jpg",
                "2022년 9월 2일 22:05분",
                "커뮤니티 테스트 내용\n하나 하나 하나"
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


}