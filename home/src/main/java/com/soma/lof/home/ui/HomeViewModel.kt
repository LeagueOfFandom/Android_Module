package com.soma.lof.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.lof.common.domain.DataStoreUseCase
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.common.repository.HomeRepository
import com.soma.lof.common.repository.HomeRepositoryImpl
import com.soma.lof.common.domain.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val homeUseCase: HomeUseCase,
    private val dataStoreUseCase: DataStoreUseCase
): ViewModel() {

    val homeBannerData = listOf(
        "https://byline.network/wp-content/uploads/2018/05/cat.png",
        "https://t1.daumcdn.net/cfile/tistory/24283C3858F778CA2E",
        "https://helpx.adobe.com/content/dam/help/en/photoshop/using/quick-actions/remove-background-before-qa1.png"
    )

    private val _homeData = MutableStateFlow((homeRepository as HomeRepositoryImpl).dummy)
    val homeData : StateFlow<List<CommonItem>> = _homeData

    init {
        getHomeApi()
    }

    private fun getHomeApi() {
        viewModelScope.launch {
            val jwtToken = "test"
            _homeData.value = homeUseCase.getHomeData(jwtToken)
        }
    }

    companion object {
        const val TAG = "HomeViewModel"
    }


}