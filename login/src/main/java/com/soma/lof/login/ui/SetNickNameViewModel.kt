package com.soma.lof.login.ui

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soma.common.ui.route.FeatureSelectTeamRouteContract
import com.soma.lof.domain.usecase.DataStoreUseCase
import com.soma.lof.domain.usecase.UserUseCase
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
    private val userUseCase: UserUseCase,
    private val dataStoreUseCase: DataStoreUseCase
): ViewModel() {

    private val _nickNameSetSuccess = MutableStateFlow(false)
    val nickNameSetSuccess get() = _nickNameSetSuccess

    fun setNickName(nickName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val jwtToken = dataStoreUseCase.jwtToken.first()

            if (jwtToken != null) {
                userUseCase.setUserNickName(jwtToken, nickName).collectLatest {
                    _nickNameSetSuccess.value = true
                }
            }
        }
    }

    fun navigateSelectTeam(activity: Activity, vararg flag: Int) {
        featureSelectTeamRouteContract.present(activity, flag)
    }
}