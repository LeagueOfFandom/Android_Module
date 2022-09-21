package com.soma.lof.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.lof.common.data.entity.CommonItem
import com.soma.lof.home.repository.HomeRepository
import com.soma.lof.home.repository.HomeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel() {


    val homeBannerData = listOf(
        "https://byline.network/wp-content/uploads/2018/05/cat.png",
        "https://t1.daumcdn.net/cfile/tistory/24283C3858F778CA2E",
        "https://helpx.adobe.com/content/dam/help/en/photoshop/using/quick-actions/remove-background-before-qa1.png"
    )
    val homeData : MutableStateFlow<List<CommonItem>> = MutableStateFlow(
        (homeRepository as HomeRepositoryImpl).data
    )

    init {
        viewModelScope.launch {
            homeRepository.getHomeApi().collectLatest {
                homeData.value = it
            }
        }
    }

}