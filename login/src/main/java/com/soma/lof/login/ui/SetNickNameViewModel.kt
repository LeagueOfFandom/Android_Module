package com.soma.lof.login.ui

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.common_ui.route.FeatureSelectTeamRouteContract
import com.soma.lof.common.domain.DataStoreUseCase
import com.soma.lof.common.repository.UserRepository
import com.soma.lof.foundation.result.data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetNickNameViewModel @Inject constructor(
    private val featureSelectTeamRouteContract: FeatureSelectTeamRouteContract,
    private val userRepository: UserRepository,
    private val dataStoreUseCase: DataStoreUseCase
): ViewModel() {

    private val _nickNameSetSuccess = MutableStateFlow(false)
    val nickNameSetSuccess get() = _nickNameSetSuccess

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

    fun navigateSelectTeam(activity: Activity, vararg flag: Int) {
        featureSelectTeamRouteContract.present(activity, flag)
    }
}