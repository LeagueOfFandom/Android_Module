package com.soma.lof.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.lof.core.model.dto.CommonItem
import com.soma.lof.core.result.UiState
import com.soma.lof.domain.usecase.DataStoreUseCase
import com.soma.lof.domain.usecase.InfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val dataStoreUseCase: DataStoreUseCase,
    private val infoUseCase: InfoUseCase
) : ViewModel() {

    private val _infoData = MutableStateFlow<UiState<List<CommonItem>>>(UiState.Loading)
    val infoData: StateFlow<UiState<List<CommonItem>>> get() = _infoData

    fun getUserInfoList() {
        viewModelScope.launch {
            val jwtToken = dataStoreUseCase.jwtToken.first()

            if (jwtToken != null) {
                infoUseCase.getUserInfo(jwtToken).collectLatest {
                    _infoData.value = it
                }
            }
        }
    }
}