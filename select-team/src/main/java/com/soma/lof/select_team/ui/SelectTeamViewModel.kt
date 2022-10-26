package com.soma.lof.select_team.ui

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.common_ui.route.FeatureHomeRouteContract
import com.soma.lof.common.usecase.DataStoreUseCase
import com.soma.lof.common.usecase.SelectTeamUseCase
import com.soma.lof.core_model.dto.domain.SelectTeamModel
import com.soma.lof.core_model.entity.TeamInfo
import com.soma.lof.core_network.result.UiState
import com.soma.lof.core_network.result.data
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
    private val featureHomeRouteContract: FeatureHomeRouteContract,
) : ViewModel(){

    private val _tabItems: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    private val _userTeamInfo: MutableStateFlow<MutableList<TeamInfo>> = MutableStateFlow(
        mutableListOf()
    )
    private val _teamCnt = MutableStateFlow(0)

    val teamCnt: StateFlow<Int> get() = _teamCnt
    val tabItems: StateFlow<List<String>> get() = _tabItems
    val userTeamInfo: StateFlow<MutableList<TeamInfo>> get() = _userTeamInfo

    private val _selectTeamData: MutableStateFlow<UiState<SelectTeamModel>> =
        MutableStateFlow(UiState.Loading)
    val selectTeamData: StateFlow<UiState<SelectTeamModel>> get() = _selectTeamData

    init {
        viewModelScope.launch {
            val jwtToken = dataStoreUseCase.jwtToken.first()
            if (jwtToken != null) {
                teamUseCase.getSelectTeamData(jwtToken).collect { result ->
                    _tabItems.value = result.data?.leagueList ?: emptyList()
                    _userTeamInfo.value = result.data?.teamInfo ?: mutableListOf()
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

    fun submitUserTeamList(activity: Activity) {
        val userTeamIdList = userTeamInfo.value.map { it.teamId }
        viewModelScope.launch {
            val jwtToken = dataStoreUseCase.jwtToken.first()
            if (jwtToken != null) {
                teamUseCase.postSelectTeamData(jwtToken, userTeamIdList).collectLatest {
                    navigateHome(activity)
                }
            }
        }
    }

    fun navigateHome(activity: Activity) {
        featureHomeRouteContract.present(activity,
            intArrayOf(Intent.FLAG_ACTIVITY_CLEAR_TASK, Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    companion object {
        private val TAG = "SelectTeamViewModel"
    }
}