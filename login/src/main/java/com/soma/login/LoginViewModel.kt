package com.soma.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.soma.common.repository.UserRepository
import com.soma.common.domain.DataStoreUseCase
import com.soma.lof.foundation.data.dto.UserTokenRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    private val dataStoreUseCase: DataStoreUseCase,
    private val userRepository: UserRepository,
) : AndroidViewModel(application) {

    private var mGoogleSignInClient: GoogleSignInClient //
    private var gsa: GoogleSignInAccount? // 기존에 로그인했던 계정
    val newUserFlow = MutableStateFlow(false)

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(google_login_web_key)
            .requestServerAuthCode(google_login_web_key)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(application, gso)

        gsa = GoogleSignIn.getLastSignedInAccount(application.baseContext)
    }

    fun getGoogleSignInClient() = mGoogleSignInClient

    fun getUserTokenInfo(googleAccessToken: String) {
        viewModelScope.launch {
            userRepository.postUserToken(UserTokenRequest(dataStoreUseCase.fcmToken.first(), googleAccessToken)).collectLatest {
                dataStoreUseCase.editJwtToken(it.jwtToken)
                newUserFlow.value = it.newUser
                Log.d(TAG, "getUserTokenInfo: ${it.newUser} ${it.jwtToken}")
            }
        }
    }

    companion object {
        const val TAG = "LoginViewModel"
    }
}