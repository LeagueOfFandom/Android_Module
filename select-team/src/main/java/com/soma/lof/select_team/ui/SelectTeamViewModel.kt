package com.soma.lof.select_team.ui

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.lof.common.domain.DataStoreUseCase
import com.soma.lof.common.repository.TeamRepository
import com.soma.common_ui.route.FeatureHomeRouteContract
import com.soma.lof.core_model.entity.LeagueTeamInfo
import com.soma.lof.core_model.entity.TeamInfo
import com.soma.lof.select_team.repository.SelectTeamFakeRepository
import com.soma.lof.select_team.repository.SelectTeamRepository
import com.soma.lof.select_team.util.test_token
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectTeamViewModel @Inject constructor(
    private val selectTeamRepository: SelectTeamRepository,
    private val teamRepository: TeamRepository,
    private val dataStoreUseCase: DataStoreUseCase,
    private val featureHomeRouteContract: FeatureHomeRouteContract,
    private val fakeRepository: SelectTeamFakeRepository,
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
            awaitAll(
                async { getTeamTotalList() },
                async { getUserTeamList() }
            )
        }
    }

    suspend fun getTeamTotalList() {
        Log.d(TAG, "getTeamTotalList Start")
        selectTeamRepository.getSelectTeamList(test_token).collectLatest {
            _tabItems.value = it.leagueList
            _leagueTeamInfo.value = it.leagueInfo
            Log.d(TAG, "getTeamTotalList End")
        }
    }

    suspend fun getUserTeamList() {
        Log.d(TAG, "getUserTeamList Start")

        teamRepository.getUserTeamList(test_token).collectLatest { teamInfoList ->
            _userTeamInfo.value = teamInfoList
            _teamCnt.value = teamInfoList.size
            Log.d(TAG, "getUserTeamList End")
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