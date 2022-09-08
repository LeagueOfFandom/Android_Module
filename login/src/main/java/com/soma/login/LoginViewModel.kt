package com.soma.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private var mGoogleSignInClient: GoogleSignInClient //
    private var gsa: GoogleSignInAccount? // 기존에 로그인했던 계정

    init {
        val gso  = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
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

}