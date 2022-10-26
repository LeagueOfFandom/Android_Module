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
    val todayDate = MutableStateFlow("")

    init {
        todayDate.value = convertTimestampToMonthDate()
        getMatchList()
    }

    private fun getMatchList(onlyMyTeam: Boolean = true) {
        viewModelScope.launch {
            val jwtToken = dataStoreUseCase.jwtToken.first()

            if (jwtToken != null) {
                matchUseCase.getMatchList(jwtToken, todayDate.value, onlyMyTeam).collectLatest {
                    _matchData.value = it

                }
            }
        }
    }

    fun setMyTeamSwitchEvent(isTurnOn: Boolean) {
        _matchData.value = UiState.Loading
        getMatchList(isTurnOn)
    }

    private fun convertTimestampToMonthDate() : String{
        val currentTime = System.currentTimeMillis()
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)

        return sdf.format(currentTime)
    }
}