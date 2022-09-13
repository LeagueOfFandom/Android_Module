package com.soma.lof.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.soma.common.base.BaseActivity
import com.soma.lof.login.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel by viewModels<LoginViewModel>()
    private lateinit var startGoogleLoginForResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this@LoginActivity
        supportActionBar?.hide()

        initGoogleLogin()
    }

    fun passLogin() {
        viewModel.navigateSelectTeam(this@LoginActivity, Intent.FLAG_ACTIVITY_CLEAR_TASK, Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    private fun initGoogleLogin() {
        startGoogleLoginForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == RESULT_OK) {
                    result.data?.let { data ->
                        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                        try {
                            val account = task.getResult(ApiException::class.java)
                            Log.d(TAG, "googleLoginToken: ${account.idToken}")

                            if (account.idToken != null) {
                                Log.d(TAG, "viewModel 호출")
                                viewModel.getUserTokenInfo(account.idToken!!)
                            } else {
                                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: ApiException) {
                            Log.e(TAG, "Google Result Error $result")
                        }
                    }
                } else {
                    Log.e(TAG, "initGoogleLogin: ${result.resultCode} ${result.data?.data} ")
                }
            }
        Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show()
    }

    fun googleLogin() {
        Log.e(TAG, "click")

        startGoogleLoginForResult.launch(viewModel.getGoogleSignInClient().signInIntent)
    }

    companion object {
        private const val TAG = "LoginActivity"
    }
}