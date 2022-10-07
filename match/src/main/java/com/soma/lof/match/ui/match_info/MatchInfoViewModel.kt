package com.soma.lof.match.ui.match_info

import androidx.lifecycle.ViewModel
import com.soma.lof.common.domain.DataStoreUseCase
import com.soma.lof.common.domain.MatchUseCase
import com.soma.lof.common.repository.MatchRepository
import com.soma.lof.common.repository.MatchRepositoryImpl
import com.soma.lof.core_model.dto.MatchInfoDummyResponse
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
    private val _matchInfo: MutableStateFlow<MatchInfoDummyResponse> = MutableStateFlow((matchRepository as MatchRepositoryImpl).matchInfoDummy)
    val matchInfo: StateFlow<MatchInfoDummyResponse> = _matchInfo
}