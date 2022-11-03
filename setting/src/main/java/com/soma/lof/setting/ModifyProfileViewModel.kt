package com.soma.lof.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.lof.core.result.UiState
import com.soma.lof.domain.usecase.DataStoreUseCase
import com.soma.lof.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ModifyProfileViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val dataStoreUseCase: DataStoreUseCase,
) : ViewModel() {

    private val _userNickName = MutableStateFlow<UiState<String>>(UiState.Loading)
    val userNickName: StateFlow<UiState<String>> get() = _userNickName

    private val _nickNameSetSuccess = MutableStateFlow(false)
    val nickNameSetSuccess get() = _nickNameSetSuccess

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val jwtToken = dataStoreUseCase.jwtToken.first()

            if (jwtToken != null) {
                userUseCase.getUserNickName(jwtToken).collectLatest {
                    _userNickName.value = it
                }
            }
        }
    }

    fun setNickName(nickName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val jwtToken = dataStoreUseCase.jwtToken.first()
            Timber.tag("Modify").d("SetNickName: %s", nickName)
            if (jwtToken != null) {
                userUseCase.setUserNickName(jwtToken, nickName).collectLatest {
                    _nickNameSetSuccess.value = true
                }
            }
        }
    }
}