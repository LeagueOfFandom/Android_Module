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
import com.soma.lof.common.domain.DataStoreUseCase
import com.soma.lof.common.repository.UserRepository
import com.soma.lof.foundation.result.Result
import com.soma.lof.foundation.result.data
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
    private val userRepository: UserRepository,
    private val featureLoginRouteContract: FeatureLoginRouteContract,
    private val dataStoreUseCase: DataStoreUseCase,
) : AndroidViewModel(application) {

    private var mGoogleSignInClient: GoogleSignInClient

    private val _userNickName = MutableStateFlow<Result<String>>(Result.Loading)
    val userNickName: StateFlow<Result<String>> get() = _userNickName

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(application, gso)
    }

    fun getUserNickName() {
        viewModelScope.launch(Dispatchers.IO) {
            val jwtToken = dataStoreUseCase.jwtToken.first()
            if (jwtToken != null) {
                userRepository.getUserNickName(jwtToken).collectLatest {
                    _userNickName.value = it
                }
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
}