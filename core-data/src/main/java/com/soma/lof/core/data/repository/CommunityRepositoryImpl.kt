package com.soma.lof.core.data.repository

import com.soma.lof.core.model.entity.PhotoItem
import com.soma.lof.core.model.entity.PostItem
import com.soma.lof.core.network.exception.NetworkFailureException
import com.soma.lof.core.result.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class CommunityRepositoryImpl : CommunityRepository {
    override suspend fun getFakeCommunitydata(jwtToken: String): Flow<UiState<List<PostItem>>> {
        return flow {
            val fakeCommunityData = listOf(
                PostItem(
                    0L,
                    "",
                    "T1 조아",
                    "2022년 9월 20일",
                    listOf(
                        PhotoItem(0L, "https://cdnweb01.wikitree.co.kr/webdata/editor/202211/09/202211091701153268.jpg"),
                        PhotoItem(1L, "https://static.inven.co.kr/column/2022/10/31/news/i8222523861.jpg")
                    ),
                    "마포고 플랜카드 봄? ㅋㅋㅋㅋㅋㅋ",
                    "10+",
                    "6+",
                ),
                PostItem(
                    2L,
                    "",
                    "DRX 우승했자너",
                    "2022년 9월 20일",
                    listOf(
                        PhotoItem(6L, "https://i.ytimg.com/vi/wIgEjroQcvA/maxresdefault.jpg"),
                    ),
                    "ㅋㅋ~",
                    "10+",
                    "20+",
                ),
                PostItem(
                    4L,
                    "",
                    "T1아... 우승해줘",
                    "2022년 9월 20일",
                    listOf(),
                    "롤드컵 너무 아쉽고....",
                    "3+",
                    "2+",
                ),
                PostItem(
                    6L,
                    "",
                    "Wow WOw",
                    "2022년 9월 20일",
                    listOf(
                        PhotoItem(6L, "https://i.ytimg.com/vi/JSd69en7ntU/maxresdefault.jpg"),
                        ),
                    "베릴 너무 잘해... 최고야...",
                    "7",
                    "3",
                ),
                PostItem(
                    7L,
                    "",
                    "체크 체크",
                    "2022년 9월 20일",
                    listOf(),
                    "ㅋㅋ",
                    "0",
                    "0",
                )
            )

            emit(UiState.Success(fakeCommunityData))
        }.catch {
            throw NetworkFailureException("Network Error ${it.message}")
        }
    }
}