package com.soma.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.soma.common.util.HOME_ACTIVITY_CLASS
import com.soma.login.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var startGoogleLoginForResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.activity = this@LoginActivity
        supportActionBar?.hide()
    }

    fun passLogin() {
        val intent = Intent(this@LoginActivity, Class.forName(HOME_ACTIVITY_CLASS)).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }

    fun googleLogin() {
        startGoogleLoginForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == RESULT_OK) {
                    result.data?.let { data ->
                        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                        try {
                            val account = task.getResult(ApiException::class.java)
                            Log.i(TAG, "googleLoginToken: ${account.idToken}")
                            // TODO Server Api 연결해야함.
                        } catch (e: ApiException) {
                            Log.w(TAG, "Google Result Error $result")
                        }
                    }
                }
            }
        Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "LoginActivity"
    }
}