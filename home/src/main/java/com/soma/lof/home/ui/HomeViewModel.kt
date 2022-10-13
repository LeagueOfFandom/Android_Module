package com.soma.lof.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.lof.common.domain.DataStoreUseCase
import com.soma.lof.common.domain.HomeUseCase
import com.soma.lof.common.repository.HomeRepository
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.foundation.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val homeUseCase: HomeUseCase,
    private val dataStoreUseCase: DataStoreUseCase
): ViewModel() {

    val homeBannerData = listOf(
        "https://d654rq93y7j8z.cloudfront.net/soma-bucket/lof_banner/1.jpg",
        "https://d654rq93y7j8z.cloudfront.net/soma-bucket/lof_banner/2.jpg",
        "https://d654rq93y7j8z.cloudfront.net/soma-bucket/lof_banner/3.jpg"
    )

    private val _homeData = MutableStateFlow<Result<List<CommonItem>>>(Result.Loading)
    val homeData : StateFlow<Result<List<CommonItem>>> get() = _homeData

    init {
        viewModelScope.launch {
            val jwtToken = dataStoreUseCase.jwtToken.first()
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