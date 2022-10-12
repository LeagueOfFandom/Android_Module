package com.soma.lof.match.ui.match_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.lof.common.domain.DataStoreUseCase
import com.soma.lof.common.domain.MatchUseCase
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.foundation.result.Result
import com.soma.lof.foundation.result.data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val matchUseCase: MatchUseCase,
    private val dataStoreUseCase: DataStoreUseCase,
) : ViewModel() {

    private val _matchData = MutableStateFlow<Result<List<CommonItem>>>(Result.Loading)
    val matchData: StateFlow<Result<List<CommonItem>>> = _matchData

    fun getMatchList(date: String, isAll: Boolean = false) {
        viewModelScope.launch {
            val jwtToken = dataStoreUseCase.jwtToken.first()

            if (jwtToken != null) {
                matchUseCase.getMatchList(jwtToken, date, isAll).collectLatest {
                    _matchData.value = it
                }
            }
        }
    }
}