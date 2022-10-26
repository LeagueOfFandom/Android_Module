package com.soma.lof.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.lof.common.usecase.InfoUseCase
import com.soma.lof.core_model.dto.CommonItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val infoUseCase: InfoUseCase
) : ViewModel() {

    val infoData = MutableStateFlow<List<CommonItem>>(emptyList())

    init {
        getInfoData()
    }

    fun getInfoData() {
        viewModelScope.launch {
            infoData.value = infoUseCase.getUserInfo("test")
        }
    }
}