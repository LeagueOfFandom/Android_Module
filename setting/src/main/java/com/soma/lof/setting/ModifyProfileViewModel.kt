package com.soma.lof.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.common_ui.route.FeatureLoginRouteContract
import com.soma.lof.common.domain.DataStoreUseCase
import com.soma.lof.common.repository.UserRepository
import com.soma.lof.foundation.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModifyProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dataStoreUseCase: DataStoreUseCase,
) : ViewModel() {

    private val _userNickName = MutableStateFlow<Result<String>>(Result.Loading)
    val userNickName: StateFlow<Result<String>> get() = _userNickName

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

            if (jwtToken != null) {
                userRepository.setUserNickName(jwtToken, nickName).collectLatest {
                    _nickNameSetSuccess.value = true
                }
            }
        }
    }
}