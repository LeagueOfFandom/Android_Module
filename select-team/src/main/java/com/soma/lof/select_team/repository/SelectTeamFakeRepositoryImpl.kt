package com.soma.lof.select_team.repository

import com.soma.lof.common.data.entity.TeamInfo
import com.soma.lof.select_team.model.FakeLeagueTeamList
import com.soma.lof.select_team.model.FakeSelectTeamModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Singleton

@Singleton
class SelectTeamFakeRepositoryImpl : SelectTeamFakeRepository {

    val data = FakeSelectTeamModel(
        listOf<String>(
            "LCK", "LPL", "LCO"
        ),
        listOf<FakeLeagueTeamList>(
            FakeLeagueTeamList(
                "대한민국의 LOL 프로 리그 팀의 리스트입니다.",
                listOf(
                    TeamInfo(
                        "LCK", false, 0L,
                        "https://cdn.pixabay.com/photo/2018/05/13/16/57/dog-3397110__480.jpg",
                        "T1"
                    ),
                    TeamInfo(
                        "LCK", false, 0L,
                        "https://cdn.pixabay.com/photo/2018/05/13/16/57/dog-3397110__480.jpg",
                        "T1"
                    )
                )
            ),
            FakeLeagueTeamList(
                "중국의 LOL 프로 리그 팀의 리스트입니다.",
                listOf(
                    TeamInfo(
                        "String", false, 0L,
                        "https://cdn.pixabay.com/photo/2018/05/13/16/57/dog-3397110__480.jpg",
                        "DWG"
                    ),
                    TeamInfo(
                        "lpl", false, 0L,
                        "https://cdn.pixabay.com/photo/2018/05/13/16/57/dog-3397110__480.jpg",
                        "DWG"
                    )
                )
            ),
            FakeLeagueTeamList(
                "유럽의 LOL 프로 리그 팀의 리스트입니다.",
                listOf(
                    TeamInfo(
                        "LCO", false, 0L,
                        "https://cdn.pixabay.com/photo/2018/05/13/16/57/dog-3397110__480.jpg",
                        "GENG"
                    ),
                    TeamInfo(
                        "LCO", false, 0L,
                        "https://cdn.pixabay.com/photo/2018/05/13/16/57/dog-3397110__480.jpg",
                        "GENG"
                    )
                )
            )
        )
    )

    override fun getSelectTeamList(): Flow<FakeSelectTeamModel> = flow {
        emit(data)
    }
}