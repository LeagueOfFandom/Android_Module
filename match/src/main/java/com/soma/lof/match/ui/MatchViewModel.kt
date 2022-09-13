package com.soma.lof.match.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.lof.match.model.dto.FakeMatchDataResponse
import com.soma.lof.match.model.entity.MatchData
import com.soma.lof.match.repository.FakeMatchRepository
import com.soma.lof.match.repository.FakeMatchRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val fakeMatchRepository: FakeMatchRepository
): ViewModel(){


    val matchData: MutableStateFlow<FakeMatchDataResponse> = MutableStateFlow((fakeMatchRepository as FakeMatchRepositoryImpl).data)

    init {
        viewModelScope.launch {
            fakeMatchRepository.getMatchData().collectLatest {
                matchData.value = it
            }
        }
    }
}