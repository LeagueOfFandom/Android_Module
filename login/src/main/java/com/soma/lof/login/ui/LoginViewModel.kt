package com.soma.lof.login.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInClient
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
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    private val dataStoreUseCase: DataStoreUseCase,
    private val userUseCase: UserUseCase,
    private val mGoogleSignInClient: GoogleSignInClient,
) : AndroidViewModel(application) {

    val googleLoginFlow = MutableStateFlow(false)

    private val _newUserFlow = MutableStateFlow<UiState<NewUserResponse>>(UiState.Loading)
    val newUserFlow: StateFlow<UiState<NewUserResponse>> get() = _newUserFlow

    /* 새로운 유저인지 판별 */
    fun checkNewUser(email: String) {
        viewModelScope.launch {
            userUseCase.isNewUser(email).collectLatest {
                _newUserFlow.value = it
            }
        }
    }

    fun getGoogleSignInClient() = mGoogleSignInClient

    /* 유저 생성 또는 이미 유저라면 JwtToken 가져오기 */
    fun getUserJwtToken(email: String?, displayName: String, photoUrl: String) {
        viewModelScope.launch {
            val fcmToken = dataStoreUseCase.fcmToken.first()

            if (email != null) {
                userUseCase.getJwtToken(
                    CreateUserRequest(email, displayName, fcmToken, photoUrl)
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