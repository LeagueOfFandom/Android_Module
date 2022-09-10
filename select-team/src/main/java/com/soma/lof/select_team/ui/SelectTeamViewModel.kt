package com.soma.lof.select_team.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.lof.select_team.model.FakeLeagueTeamList
import com.soma.lof.select_team.repository.SelectTeamFakeRepository
import com.soma.lof.select_team.repository.SelectTeamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SelectTeamViewModel @Inject constructor(
    private val selectTeamRepository: SelectTeamRepository,
    private val fakeRepository: SelectTeamFakeRepository
) : ViewModel() {

    private val _tabItems: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    private val _leagueTeamInfo: MutableStateFlow<List<FakeLeagueTeamList>> = MutableStateFlow(
        emptyList())
    private val _teamCnt = MutableStateFlow(0)

    val teamCnt: StateFlow<Int> get() = _teamCnt
    val tabItems: StateFlow<List<String>> get() = _tabItems
    val leagueTeamInfo: StateFlow<List<FakeLeagueTeamList>> get() =_leagueTeamInfo

    init {
        viewModelScope.launch {
            fakeRepository.getSelectTeamList().collectLatest {
                var teamCntSum = 0
                _tabItems.value = it.leagueList
                _leagueTeamInfo.value = it.leagueTeamList
                for (leagueTeam in it.leagueTeamList) {
                    for(team in leagueTeam.teamList) {
                        teamCntSum += if(team.teamCheck) 1 else 0
                    }
                }
                _teamCnt.emit(teamCntSum)
                Log.d(SelectTeamActivity.TAG, "teamCnt init ${teamCnt.value}")
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

    companion object {
        private val TAB_ITEMS = listOf("1", "2", "3")
    }

}