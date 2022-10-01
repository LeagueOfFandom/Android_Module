package com.soma.lof.select_team.ui

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.common_ui.route.FeatureHomeRouteContract
import com.soma.lof.common.domain.DataStoreUseCase
import com.soma.lof.common.repository.SelectTeamRepository
import com.soma.lof.common.repository.TeamRepository
import com.soma.lof.core_model.entity.LeagueTeamInfo
import com.soma.lof.core_model.entity.TeamInfo
import com.soma.lof.select_team.util.test_token
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/* TODO 추후 Repository를 UseCase로 리팩토링 */
@HiltViewModel
class SelectTeamViewModel @Inject constructor(
    private val selectTeamRepository: SelectTeamRepository,
    private val teamRepository: TeamRepository,
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

    init {
        viewModelScope.launch {
            val jwtToken = dataStoreUseCase.jwtToken.first()
            if (jwtToken != null) {
                awaitAll(
                    async { getTeamTotalList(jwtToken) },
                    async { getUserTeamList(jwtToken) }
                )
            }
        }
    }

    private suspend fun getTeamTotalList(jwtToken: String) {
        selectTeamRepository.getSelectTeamList(jwtToken).collectLatest {
            _tabItems.value = it.leagueList
            _leagueTeamInfo.value = it.leagueInfo
        }
    }

    private suspend fun getUserTeamList(jwtToken: String) {
        teamRepository.getUserTeamList(jwtToken).collectLatest { teamInfoList ->
            _userTeamInfo.value = teamInfoList
            _teamCnt.value = teamInfoList.size
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
        featureHomeRouteContract.present(activity, intArrayOf(Intent.FLAG_ACTIVITY_CLEAR_TASK, Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    companion object {
        private val TAG = "SelectTeamViewModel"
    }

}