package com.soma.lof.match.ui.match_info

import androidx.lifecycle.ViewModel
import com.soma.lof.domain.usecase.DataStoreUseCase
import com.soma.lof.domain.usecase.MatchUseCase
import com.soma.lof.core.model.dto.MatchInfoDummyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MatchInfoViewModel @Inject constructor(
    private val matchRepository: MatchRepository,
    private val matchUseCase: MatchUseCase,
    private val dataStoreUseCase: DataStoreUseCase,
) : ViewModel() {

//
    private val _matchInfo: MutableStateFlow<MatchInfoDummyResponse> = MutableStateFlow((matchRepository as com.soma.lof.core.data.MatchRepositoryImpl).matchInfoDummy)
    val matchInfo: StateFlow<MatchInfoDummyResponse> = _matchInfo
}