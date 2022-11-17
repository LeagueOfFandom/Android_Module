package com.soma.lof.select_team.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.lof.core.model.entity.TeamInfo
import com.soma.lof.core.result.UiState
import com.soma.lof.core.result.data
import com.soma.lof.domain.model.SelectTeamModel
import com.soma.lof.domain.usecase.DataStoreUseCase
import com.soma.lof.domain.usecase.SelectTeamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectTeamViewModel @Inject constructor(
    private val teamUseCase: SelectTeamUseCase,
    private val dataStoreUseCase: DataStoreUseCase,
) : ViewModel(){

    private val _tabItems: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    var userTeamInfoList = mutableSetOf<TeamInfo>()
    private val _teamCnt = MutableStateFlow(0)

    val teamCnt: StateFlow<Int> get() = _teamCnt
    val tabItems: StateFlow<List<String>> get() = _tabItems

    private val _selectTeamData: MutableStateFlow<UiState<SelectTeamModel>> = MutableStateFlow(UiState.Loading)
    val selectTeamData: StateFlow<UiState<SelectTeamModel>> get() = _selectTeamData

    val navigateHome = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            val jwtToken = dataStoreUseCase.jwtToken.first()
            if (jwtToken != null) {
                teamUseCase.getSelectTeamData(jwtToken).collect { result ->
                    _tabItems.value = result.data?.leagueList ?: emptyList()
                    for (info in result.data?.teamInfo ?: emptyList()) {
                        userTeamInfoList.add(info)
                    }
                    _teamCnt.value = result.data?.teamInfo?.size ?: 0
                    _selectTeamData.value = result
                }
            }
        }
    }

    fun plusTeamCnt() {
        _teamCnt.value += 1
    }

    fun minusTeamCnt() {
        _teamCnt.value -= 1
    }

    fun addTeam(team: TeamInfo) {
        userTeamInfoList.add(team)
    }

    fun removeTeam(team: TeamInfo) {
        userTeamInfoList =  userTeamInfoList.toList().filter { it.teamId != team.teamId }.toMutableSet()
        userTeamInfoList.remove(team)
    }

    fun submitUserTeamList() {
        val userTeamIdList = userTeamInfoList.map { it.teamId }
        viewModelScope.launch {
            val jwtToken = dataStoreUseCase.jwtToken.first()
            if (jwtToken != null) {
                teamUseCase.postSelectTeamData(jwtToken, userTeamIdList).collectLatest {
                    navigateHome.value = true
                }
            }
        }
    }
}