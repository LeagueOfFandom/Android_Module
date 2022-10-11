package com.soma.lof.select_team.ui

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.common_ui.route.FeatureHomeRouteContract
import com.soma.lof.common.domain.DataStoreUseCase
import com.soma.lof.common.domain.TeamUseCase
import com.soma.lof.common.repository.TeamRepository
import com.soma.lof.core_model.dto.domain.SelectTeamModel
import com.soma.lof.core_model.entity.LeagueTeamInfo
import com.soma.lof.core_model.entity.TeamInfo
import com.soma.lof.foundation.result.Result
import com.soma.lof.foundation.result.data
import com.soma.lof.select_team.ui.SelectTeamActivity.Companion.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class SelectTeamViewModel @Inject constructor(
    private val teamUseCase: TeamUseCase,
    private val dataStoreUseCase: DataStoreUseCase,
    private val featureHomeRouteContract: FeatureHomeRouteContract,
) : ViewModel() {

    private val _tabItems: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    private val _leagueTeamInfo: MutableStateFlow<List<LeagueTeamInfo>> = MutableStateFlow(
        emptyList())
    private val _userTeamInfo: MutableStateFlow<List<TeamInfo>> = MutableStateFlow(emptyList())
    private val _teamCnt = MutableStateFlow(0)

    val teamCnt: StateFlow<Int> get() = _teamCnt
    val tabItems: StateFlow<List<String>> get() = _tabItems
    val leagueTeamInfo: StateFlow<List<LeagueTeamInfo>> get() = _leagueTeamInfo
    val userTeamInfo: StateFlow<List<TeamInfo>> get() = _userTeamInfo

    private val _selectTeamItems: MutableStateFlow<Result<SelectTeamModel>> =
        MutableStateFlow(Result.Loading)
    val selectTeamModel: StateFlow<Result<SelectTeamModel>> get() = _selectTeamItems

    init {
        viewModelScope.launch {
            val jwtToken = dataStoreUseCase.jwtToken.first()
            Log.d(TAG, "TeamUseCase jwtToken: $jwtToken")
            if (jwtToken != null) {
                Log.d(TAG, "TeamUseCase collect")
                teamUseCase.getSelectTeamData(jwtToken).collect { result ->
                    _leagueTeamInfo.value = result.data?.leagueInfo ?: emptyList()
                    _tabItems.value = result.data?.leagueList ?: emptyList()
                    _userTeamInfo.value = result.data?.teamInfo ?: emptyList()
                    _selectTeamItems.value = result
                }
            }
        }
    }

    fun plusTeamCnt() {
        _teamCnt.value += 1
        Log.d(SelectTeamActivity.TAG, "teamCnt plus ${teamCnt.value}")
    }

    fun minusTeamCnt() {
        Log.d(SelectTeamActivity.TAG, "teamCnt minus")
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