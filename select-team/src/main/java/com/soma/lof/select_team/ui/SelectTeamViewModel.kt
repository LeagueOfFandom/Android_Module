package com.soma.lof.select_team.ui

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.common_ui.route.FeatureHomeRouteContract
import com.soma.lof.common.domain.DataStoreUseCase
import com.soma.lof.common.domain.SelectTeamUseCase
import com.soma.lof.core_model.dto.domain.SelectTeamModel
import com.soma.lof.core_model.entity.TeamInfo
import com.soma.lof.foundation.result.Result
import com.soma.lof.foundation.result.data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
    private val _userTeamInfo: MutableStateFlow<List<TeamInfo>> = MutableStateFlow(emptyList())
    private val _teamCnt = MutableStateFlow(0)

    val teamCnt: StateFlow<Int> get() = _teamCnt
    val tabItems: StateFlow<List<String>> get() = _tabItems
    val userTeamInfo: StateFlow<List<TeamInfo>> get() = _userTeamInfo

    private val _selectTeamData: MutableStateFlow<Result<SelectTeamModel>> =
        MutableStateFlow(Result.Loading)
    val selectTeamData: StateFlow<Result<SelectTeamModel>> get() = _selectTeamData

    init {
        viewModelScope.launch {
            val jwtToken = dataStoreUseCase.jwtToken.first()
            if (jwtToken != null) {
                teamUseCase.getSelectTeamData(jwtToken).collect { result ->
                    _tabItems.value = result.data?.leagueList ?: emptyList()
                    _userTeamInfo.value = result.data?.teamInfo ?: emptyList()
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

    fun navigateHome(activity: Activity, vararg flag: Int) {
        featureHomeRouteContract.present(activity,
            intArrayOf(Intent.FLAG_ACTIVITY_CLEAR_TASK, Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    companion object {
        private val TAG = "SelectTeamViewModel"
    }
}