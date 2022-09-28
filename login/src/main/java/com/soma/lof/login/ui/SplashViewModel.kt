package com.soma.lof.login.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.messaging.FirebaseMessaging
import com.soma.lof.common.domain.DataStoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreUseCase: DataStoreUseCase,
) : ViewModel() {

    val timeOut = MutableStateFlow(false)

    init {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(SplashActivity.TAG, "Fetching FCM registeration token failed", task.exception)
                return@addOnCompleteListener
            }

            val fcmToken = task.result
            viewModelScope.launch {
                dataStoreUseCase.editFcmToken(fcmToken)
                dataStoreUseCase.fcmToken.collectLatest {
                    Log.d(TAG, "fcmToken: $it")
                }
            }
        }
    }

    companion object {
        const val TAG = "SplashViewModel"
    }
}