package com.soma.lof.login.ui

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.messaging.FirebaseMessaging
import com.soma.common_ui.route.FeatureHomeRouteContract
import com.soma.lof.common.usecase.DataStoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val featureHomeRouteContract: FeatureHomeRouteContract,
    private val dataStoreUseCase: DataStoreUseCase,
) : ViewModel() {

    val timeOut = MutableStateFlow(false)
    val autoSignIn = MutableStateFlow(false)

    init {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@addOnCompleteListener
            }

            val fcmToken = task.result
            viewModelScope.launch {
                dataStoreUseCase.editFcmToken(fcmToken)
                dataStoreUseCase.fcmToken.collectLatest {
                    Timber.tag(TAG).d("fcmToken: %s", it)
                }
            }
        }

        viewModelScope.launch {
            val jwtToken = dataStoreUseCase.jwtToken.first()
            if (jwtToken != null) {
                autoSignIn.value = true
            }
        }
    }

    fun navigateHome(activity: Activity, vararg flag: Int) {
        featureHomeRouteContract.present(activity, flag)
    }

    companion object {
        const val TAG = "SplashViewModel"
    }
}