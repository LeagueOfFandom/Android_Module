package com.soma.lof.match.ui.match_info

import androidx.lifecycle.ViewModel
import com.soma.lof.core.model.dto.MatchInfoDummyResponse
import com.soma.lof.domain.usecase.MatchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MatchInfoViewModel @Inject constructor(
    matchUseCase: MatchUseCase,
) : ViewModel() {

//
    private val _matchInfo: MutableStateFlow<MatchInfoDummyResponse> = MutableStateFlow(matchUseCase.getMatchInfoDataTest())
    val matchInfo: StateFlow<MatchInfoDummyResponse> = _matchInfo
}