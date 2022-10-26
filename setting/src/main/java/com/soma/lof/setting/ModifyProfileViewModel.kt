package com.soma.lof.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.lof.common.usecase.DataStoreUseCase
import com.soma.lof.core_network.result.UiState
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
    private val userRepository: UserRepository,
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
                userRepository.getUserNickName(jwtToken).collectLatest {
                    _userNickName.value = it
                }
            }
        }
    }

    fun setNickName(nickName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val jwtToken = dataStoreUseCase.jwtToken.first()
            Timber.tag("Modify").d("setNickName: %s", nickName)
            if (jwtToken != null) {
                userRepository.setUserNickName(jwtToken, nickName).collectLatest {
                    _nickNameSetSuccess.value = true
                }
            }
        }
    }
}