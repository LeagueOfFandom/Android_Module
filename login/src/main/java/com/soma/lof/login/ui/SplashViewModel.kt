package com.soma.lof.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.messaging.FirebaseMessaging
import com.soma.lof.domain.usecase.DataStoreUseCase
import com.soma.lof.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreUseCase: DataStoreUseCase,
    private val userUseCase: UserUseCase
) : ViewModel() {

    val timeOut = MutableStateFlow(false)
    val fcmTask = MutableStateFlow(false)
    val autoSignIn = MutableStateFlow(false)

    init {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@addOnCompleteListener
            }

            val fcmToken = task.result
            viewModelScope.launch {
                dataStoreUseCase.editFcmToken(fcmToken)
                val jwtToken = dataStoreUseCase.jwtToken.first()

                dataStoreUseCase.fcmToken.collectLatest {
                    if (jwtToken != null) {
                        userUseCase.updateFCM(jwtToken, fcmToken)
                        autoSignIn.value = true
                        Timber.tag(TAG).d("jwtToken: $jwtToken")
                    }
                    fcmTask.value = true
                }
            }
        }
    }

    companion object {
        const val TAG = "SplashViewModel"
    }
}