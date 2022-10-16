package com.soma.lof.home.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.lof.common.domain.DataStoreUseCase
import com.soma.lof.common.domain.HomeUseCase
import com.soma.lof.core_model.dto.domain.HomeModel
import com.soma.lof.foundation.result.Result
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

    private val _homeData = MutableStateFlow<Result<HomeModel>>(Result.Loading)
    val homeData : StateFlow<Result<HomeModel>> get() = _homeData

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