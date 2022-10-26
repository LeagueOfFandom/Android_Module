package com.soma.lof.login.ui

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.soma.common_ui.route.FeatureHomeRouteContract
import com.soma.lof.common.usecase.DataStoreUseCase
import com.soma.lof.core.data.UserRepository
import com.soma.lof.core_model.dto.CreateUserRequest
import com.soma.login.google_login_web_key
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    private val dataStoreUseCase: DataStoreUseCase,
    private val userRepository: UserRepository,
    private val featureHomeRouteContract: FeatureHomeRouteContract
) : AndroidViewModel(application) {

    private var mGoogleSignInClient: GoogleSignInClient //
    private var gsa: GoogleSignInAccount? // 기존에 로그인했던 계정
    val googleLoginFlow = MutableStateFlow(false)
    val newUserFlow = MutableStateFlow(false)
    val userLanguage = MutableStateFlow("")

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(google_login_web_key)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(application, gso)

        gsa = GoogleSignIn.getLastSignedInAccount(application.applicationContext)
    }

    fun getGoogleSignInClient() = mGoogleSignInClient

    fun getUserTokenInfo(email: String?, displayName: String, photoUrl: String) {
        viewModelScope.launch {
            val fcmToken = dataStoreUseCase.fcmToken.first()

            // email이 Null일 경우 toast msg 띄우기
            if (email != null) {
                userRepository.createUser(
                    CreateUserRequest(
                        email, displayName, fcmToken, photoUrl
                    )
                ).collectLatest {
                    dataStoreUseCase.editJwtToken(it.data!!.jwtToken)
                    newUserFlow.value = it.data!!.isNewUser
                    googleLoginFlow.value = true
                }
            }
        }
    }

    fun navigateHomeFragment(activity: Activity, vararg flag: Int) {
        featureHomeRouteContract.present(activity, flag)
    }

    fun testScenario() {
        viewModelScope.launch {
            dataStoreUseCase.editJwtToken("test")
            googleLoginFlow.value = true
        }
    }

    companion object {
        const val TAG = "LoginViewModel"
    }
}