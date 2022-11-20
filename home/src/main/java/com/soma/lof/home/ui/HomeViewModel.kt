package com.soma.lof.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.lof.core.result.UiState
import com.soma.lof.domain.model.HomeModel
import com.soma.lof.domain.usecase.DataStoreUseCase
import com.soma.lof.domain.usecase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
    private val dataStoreUseCase: DataStoreUseCase
): ViewModel() {

    private val _homeModelData = MutableStateFlow<UiState<HomeModel>>(UiState.Loading)
    val homeModelData : StateFlow<UiState<HomeModel>> get() = _homeModelData

    init {
        getHomeData()
    }

    fun getHomeData() {
        viewModelScope.launch {
            val jwtToken = dataStoreUseCase.jwtToken.first()
            Timber.tag(TAG).d("jwtToken: $jwtToken")
            if (jwtToken != null) {
                homeUseCase.getHomeData(jwtToken).collectLatest {
                    _homeModelData.value = it
                }
            }
        }
    }

    fun getFakeHomeData() {
        viewModelScope.launch {
            val jwtToken = dataStoreUseCase.jwtToken.first()
            Timber.tag(TAG).d("jwtToken: $jwtToken")
            if (jwtToken != null) {
                delay(1000L)
                homeUseCase.getFakeHomeData(jwtToken).collectLatest {
                    _homeModelData.value = it
                }
            }
        }
    }

    companion object {
        const val TAG = "HomeViewModel"
    }


}