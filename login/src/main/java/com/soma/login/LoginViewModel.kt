package com.soma.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.soma.common.model.dto.UserTokenRequest
import com.soma.common.model.dto.UserTokenResponse
import com.soma.common.repository.UserRepository
import com.soma.common.result.UiState
import com.soma.common.result.successOrNull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    private val userRepository: UserRepository
) : AndroidViewModel(application) {

    private var mGoogleSignInClient: GoogleSignInClient //
    private var gsa: GoogleSignInAccount? // 기존에 로그인했던 계정

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(google_login_web_key)
            .requestServerAuthCode(google_login_web_key)
            .requestEmail()
            .build()

        Log.d("LoginViewModel", "gso Init")
        mGoogleSignInClient = GoogleSignIn.getClient(application, gso)
        /*
        * TODO context 체크해야함.
        *  */
        gsa = GoogleSignIn.getLastSignedInAccount(application.baseContext)
    }

    fun getGoogleSignInClient() = mGoogleSignInClient

    fun getUserTokenInfo(fcmToken: String, googleAccessToken: String) {
        viewModelScope.launch {
            userRepository.postUserToken(UserTokenRequest(fcmToken, googleAccessToken)).collect {
//                val state = it.successOrNull()
//                Log.d(TAG, "getUserTokenInfo: ")
//                emit()
            }
        }
    }

    companion object {
        const val TAG = "LoginViewModel"
    }
}