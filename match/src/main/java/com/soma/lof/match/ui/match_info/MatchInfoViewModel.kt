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
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MatchInfoViewModel @Inject constructor(
    private val matchUseCase: MatchUseCase,
    private val dataStoreUseCase: DataStoreUseCase
) : ViewModel() {

//
    private val _matchInfo: MutableStateFlow<MatchInfoDummyResponse> = MutableStateFlow(matchUseCase.getMatchInfoDataTest())
    val matchInfo: StateFlow<MatchInfoDummyResponse> = _matchInfo

    private val _matchDetail: MutableStateFlow<UiState<MatchDetailResponse>> = MutableStateFlow(UiState.Loading)
    val matchDetail: StateFlow<UiState<MatchDetailResponse>> get() = _matchDetail

    private var currentSet = 0
    private val _matchDetailSetInfo: MutableStateFlow<TeamVsTeamSetInfo?> = MutableStateFlow(_matchDetail.value.data?.body?.setInfoList?.get(currentSet))
    val matchDetailSetInfo: StateFlow<TeamVsTeamSetInfo?> get() = _matchDetailSetInfo

    fun getMatchDetail(matchId: Long) {
        viewModelScope.launch {
            val jwtToken = dataStoreUseCase.jwtToken.first()

            if (jwtToken != null) {
                matchUseCase.getMatchDetail(jwtToken, matchId).collectLatest {
                    _matchDetail.value = it
                    _matchDetailSetInfo.value = _matchDetail.value.data!!.body!!.setInfoList[currentSet]
                }
            }
        }
    }

    fun setMatchSetPos(position: Int) {
        currentSet = position
        _matchDetailSetInfo.value = _matchDetail.value.data?.body?.setInfoList?.get(currentSet)
    }
}