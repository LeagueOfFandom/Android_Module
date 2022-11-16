package com.soma.lof.community

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.lof.core.model.entity.PostItem
import com.soma.lof.core.result.UiState
import com.soma.lof.domain.usecase.CommunityUseCase
import com.soma.lof.domain.usecase.DataStoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val communityUseCase: CommunityUseCase,
    private val dataStoreUseCase: DataStoreUseCase
): ViewModel() {

    private val _communityData = MutableStateFlow<UiState<List<PostItem>>>(UiState.Loading)
    val communityData : StateFlow<UiState<List<PostItem>>> get() = _communityData

    init {
        getFakeCommunityData()
    }

    fun getFakeCommunityData() {
        viewModelScope.launch {
            val jwtToken = dataStoreUseCase.jwtToken.first()
            if (jwtToken != null) {
                delay(1000L)
                communityUseCase.getFakeCommunityData(jwtToken).collectLatest {
                    _communityData.value = it
                }
            }
        }
    }
}
