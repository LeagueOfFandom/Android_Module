package com.soma.lof.setting

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.soma.lof.core.result.UiState
import com.soma.lof.domain.model.SettingModel
import com.soma.lof.domain.usecase.DataStoreUseCase
import com.soma.lof.domain.usecase.SettingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    application: Application,
    private val settingUseCase: SettingUseCase,
    private val dataStoreUseCase: DataStoreUseCase,
    private val mGoogleSignInClient: GoogleSignInClient
) : AndroidViewModel(application) {

    private val _settingData = MutableStateFlow<UiState<SettingModel>>(UiState.Loading)
    val settingData: StateFlow<UiState<SettingModel>> get() = _settingData

    val signOutFlow = MutableStateFlow(false)

    fun getSettingData() {
        viewModelScope.launch(Dispatchers.IO) {
            val jwtToken = dataStoreUseCase.jwtToken.first()
            if (jwtToken != null) {
                settingUseCase.getUserSettingData(jwtToken).collectLatest {
                    _settingData.value = it
                }
            }
        }
    }

    fun postUserMatchAlarm(alarm: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val jwtToken = dataStoreUseCase.jwtToken.first()
            if (jwtToken != null) {
                settingUseCase.postUserMatchAlarm(jwtToken, alarm)
            }
        }
    }

    fun signOut() {
        mGoogleSignInClient.signOut().addOnCompleteListener {
            viewModelScope.launch(Dispatchers.IO) {
                dataStoreUseCase.removeJwtToken()
                signOutFlow.value = true
            }
        }
    }
}