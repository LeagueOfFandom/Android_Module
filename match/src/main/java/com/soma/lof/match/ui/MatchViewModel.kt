package com.soma.lof.match.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.lof.common.repository.MatchRepository
import com.soma.lof.common.repository.MatchRepositoryImpl
import com.soma.lof.core_model.dto.MatchResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val matchRepository: MatchRepository
): ViewModel(){


    val matchData: MutableStateFlow<MatchResponse> = MutableStateFlow((matchRepository as MatchRepositoryImpl).data)

    init {
        viewModelScope.launch {
            matchRepository.getMatchData().collectLatest {
                matchData.value = it
            }
        }
    }
}