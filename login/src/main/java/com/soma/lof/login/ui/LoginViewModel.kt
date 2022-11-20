package com.soma.lof.login.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.soma.lof.core.model.dto.CreateUserRequest
import com.soma.lof.core.model.entity.NewUserResponse
import com.soma.lof.core.result.UiState
import com.soma.lof.core.result.data
import com.soma.lof.domain.usecase.DataStoreUseCase
import com.soma.lof.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    private val dataStoreUseCase: DataStoreUseCase,
    private val userUseCase: UserUseCase,
) : AndroidViewModel(application) {

    private var mGoogleSignInClient: GoogleSignInClient //
    private var gsa: GoogleSignInAccount? // 기존에 로그인했던 계정
    val googleLoginFlow = MutableStateFlow(false)

    private val _newUserFlow = MutableStateFlow<UiState<NewUserResponse>>(UiState.Loading)
    val newUserFlow: StateFlow<UiState<NewUserResponse>> get() = _newUserFlow
    val userLanguage = MutableStateFlow("")

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(application, gso)

        gsa = GoogleSignIn.getLastSignedInAccount(application.applicationContext)
    }

    fun checkNewUser(email: String) {
        viewModelScope.launch {
            userUseCase.isNewUser(email).collectLatest {
                _newUserFlow.value = it
            }
        }
    }

    fun getGoogleSignInClient() = mGoogleSignInClient

    fun getUserTokenInfo(email: String?, displayName: String, photoUrl: String) {
        viewModelScope.launch {
            val fcmToken = dataStoreUseCase.fcmToken.first()

            if (email != null) {
                userUseCase.createUser(
                    CreateUserRequest(
                        email, displayName, fcmToken, photoUrl
                    )
                ).collectLatest {
                    dataStoreUseCase.editJwtToken(it.data!!.jwtToken)
                    googleLoginFlow.value = true
                }
            }
        }
    }

    companion object {
        const val TAG = "LoginViewModel"
    }
}