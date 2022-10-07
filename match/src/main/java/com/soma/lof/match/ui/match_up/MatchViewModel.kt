package com.soma.lof.match.ui.match_up

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.lof.common.domain.DataStoreUseCase
import com.soma.lof.common.domain.MatchUseCase
import com.soma.lof.common.repository.HomeRepository
import com.soma.lof.common.repository.MatchRepository
import com.soma.lof.common.repository.MatchRepositoryImpl
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.dto.MatchInfoDummyResponse
import com.soma.lof.core_model.dto.MatchInfoResponse
import com.soma.lof.core_model.entity.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val matchRepository: MatchRepository,
    private val matchUseCase: MatchUseCase,
    private val dataStoreUseCase: DataStoreUseCase,
) : ViewModel() {

    private val _matchData = MutableStateFlow((matchRepository as MatchRepositoryImpl).data)
    val matchData: StateFlow<List<CommonItem>> = _matchData


    fun getMatchList(date: String, isAll: Boolean = false) {
        viewModelScope.launch {
            val jwtToken = dataStoreUseCase.jwtToken.first()

            if (jwtToken != null) {
                _matchData.value =  matchUseCase.getMatchList(jwtToken, date, isAll)
            }
        }
    }
}