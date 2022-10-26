package com.soma.lof.setting

import android.app.Activity
import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.soma.common_ui.route.FeatureLoginRouteContract
import com.soma.common_ui.route.FeatureSelectTeamRouteContract
import com.soma.lof.common.usecase.DataStoreUseCase
import com.soma.lof.common.usecase.SettingUseCase
import com.soma.lof.core_model.dto.domain.SettingModel
import com.soma.lof.core_network.result.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    application: Application,
    private val settingUseCase: SettingUseCase,
    private val featureLoginRouteContract: FeatureLoginRouteContract,
    private val dataStoreUseCase: DataStoreUseCase,
    private val featureSelectTeamRouteContract: FeatureSelectTeamRouteContract
) : AndroidViewModel(application) {

    private var mGoogleSignInClient: GoogleSignInClient

    private val _settingData = MutableStateFlow<UiState<SettingModel>>(UiState.Loading)
    val settingData: StateFlow<UiState<SettingModel>> get() = _settingData


    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(application, gso)
    }

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

    fun signOut(activity: Activity) {
        mGoogleSignInClient.signOut().addOnCompleteListener {
            viewModelScope.launch {
                withContext(Dispatchers.Default) {
                    dataStoreUseCase.removeJwtToken()
                    featureLoginRouteContract.present(activity, intArrayOf(Intent.FLAG_ACTIVITY_CLEAR_TASK, Intent.FLAG_ACTIVITY_NEW_TASK))
                }
            }
        }
    }

    fun selectTeam(activity: Activity) {
        featureSelectTeamRouteContract.present(activity,  intArrayOf(Intent.FLAG_ACTIVITY_NEW_TASK))
    }
}