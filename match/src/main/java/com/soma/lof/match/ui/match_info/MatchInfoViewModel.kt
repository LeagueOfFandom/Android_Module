package com.soma.lof.match.ui.match_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.lof.core.model.dto.MatchDetailResponse
import com.soma.lof.core.model.dto.MatchInfoDummyResponse
import com.soma.lof.core.model.entity.TeamVsTeamSetInfo
import com.soma.lof.core.result.UiState
import com.soma.lof.core.result.data
import com.soma.lof.domain.usecase.DataStoreUseCase
import com.soma.lof.domain.usecase.MatchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchInfoViewModel @Inject constructor(
    private val matchUseCase: MatchUseCase,
    private val dataStoreUseCase: DataStoreUseCase
) : ViewModel() {

    private var prevSet = 0
    private var currentSet = 0
    private var setSize = 0

    private val _matchDummyInfo: MutableStateFlow<MatchInfoDummyResponse> = MutableStateFlow(matchUseCase.getMatchInfoDataTest())
    val matchDummyInfo: StateFlow<MatchInfoDummyResponse> = _matchDummyInfo

    private val _matchDetail: MutableStateFlow<UiState<MatchDetailResponse>> = MutableStateFlow(UiState.Loading) // 전체 세트의 경기 세부정보
    private val _matchDetailSetInfo: MutableStateFlow<TeamVsTeamSetInfo?> = MutableStateFlow(_matchDetail.value.data?.body?.setInfoList?.get(currentSet)) // 해당 세트의 경기 정보
    val matchDetailSetInfo: StateFlow<TeamVsTeamSetInfo?> get() = _matchDetailSetInfo

    fun getMatchDetail(matchId: Long) {
        viewModelScope.launch {
            val jwtToken = dataStoreUseCase.jwtToken.first()

            if (jwtToken != null) {
                matchUseCase.getMatchDetail(jwtToken, matchId).collectLatest {
                    _matchDetail.value = it
                    _matchDetailSetInfo.value = _matchDetail.value.data!!.body!!.setInfoList[currentSet]
                    setSize = _matchDetail.value.data!!.body!!.setInfoList.size
                }
            }
        }
    }

    fun setMatchSetPos(position: Int) {
        prevSet = currentSet
        currentSet = position
        _matchDetailSetInfo.value = _matchDetail.value.data?.body?.setInfoList?.get(currentSet)
    }

    fun getCurrentSet() = currentSet
    fun getSetSize() = setSize
}