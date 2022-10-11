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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    application: Application,
    private val featureLoginRouteContract: FeatureLoginRouteContract,
    private val dataStoreUseCase: DataStoreUseCase,
) : AndroidViewModel(application) {

    private var mGoogleSignInClient: GoogleSignInClient

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(application, gso)
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