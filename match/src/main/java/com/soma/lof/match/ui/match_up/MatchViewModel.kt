package com.soma.lof.match.ui.match_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.lof.core.model.dto.CommonItem
import com.soma.lof.core.result.UiState
import com.soma.lof.domain.usecase.DataStoreUseCase
import com.soma.lof.domain.usecase.MatchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val matchUseCase: MatchUseCase,
    private val dataStoreUseCase: DataStoreUseCase,
) : ViewModel() {


    private val _matchData = MutableStateFlow<UiState<List<CommonItem>>>(UiState.Loading)
    val matchData: StateFlow<UiState<List<CommonItem>>> get()= _matchData
    var todayYear: String = "2022"
    var todayMonth: String = "11"
    var todayDay: String = "01"

    val dateChangeFlow = MutableStateFlow(false)

    init {
        convertTimestampToMonthDate()
        getMatchList()
        dateChangeFlow.value = true
    }

    fun getMatchList(onlyMyTeam: Boolean = true) {
        viewModelScope.launch {
            val jwtToken = dataStoreUseCase.jwtToken.first()

            if (jwtToken != null) {
                matchUseCase.getMatchList(jwtToken, "${todayYear}-${todayMonth}-${todayDay}", onlyMyTeam).collectLatest {
                    _matchData.value = it
                }
            }
        }
    }

    fun monthPrevBtnClick() {
        if(todayMonth.toInt() - 1 < 0) {
            todayMonth = "12"
            todayYear = (todayYear.toInt()-1).toString()
        } else {
            todayMonth = (todayMonth.toInt()-1).toString()
        }
        if (todayMonth.length < 2) todayMonth = "0$todayMonth"
        dateChangeFlow.value = true
    }

    fun monthNextBtnClick() {
        if(todayMonth.toInt() + 1 > 12) {
            todayMonth = "1"
            todayYear = (todayYear.toInt()+1).toString()
        } else {
            todayMonth = (todayMonth.toInt()+1).toString()
        }
        if (todayMonth.length < 2) todayMonth = "0$todayMonth"
        dateChangeFlow.value = true
    }

    fun setMyTeamSwitchEvent(isTurnOn: Boolean) {
        _matchData.value = UiState.Loading
        getMatchList(isTurnOn)
    }

    private fun convertTimestampToMonthDate(){
        val currentTime = System.currentTimeMillis()
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
        val date = sdf.format(currentTime).split("-")
        todayYear = date[0]
        todayMonth = date[1]
        todayDay = date[2]
    }
}