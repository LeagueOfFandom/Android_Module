package com.soma.lof.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.lof.core.result.UiState
import com.soma.lof.domain.model.HomeModel
import com.soma.lof.domain.usecase.DataStoreUseCase
import com.soma.lof.domain.usecase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _homeData = MutableStateFlow<UiState<HomeModel>>(UiState.Loading)
    val homeData : StateFlow<UiState<HomeModel>> get() = _homeData

    init {
        viewModelScope.launch {
            val jwtToken = dataStoreUseCase.jwtToken.first()
            Timber.tag(TAG).d("jwtToken: $jwtToken")
            if (jwtToken != null) {
                homeUseCase.getHomeData(jwtToken).collectLatest {
                    _homeData.value = it
                }
            }
        }
    }

    companion object {
        const val TAG = "HomeViewModel"
    }


}