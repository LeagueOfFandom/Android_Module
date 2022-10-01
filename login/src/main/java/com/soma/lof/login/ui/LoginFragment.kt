package com.soma.lof.login.ui

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.soma.common.base.BaseFragment
import com.soma.lof.login.R
import com.soma.lof.login.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private lateinit var startGoogleLoginForResult: ActivityResultLauncher<Intent>
    private val viewModel by viewModels<LoginViewModel>()

    override fun initView() {

        bind {
            fragment = this@LoginFragment
            logoResId = R.drawable.img_logo
            subLogoResId = R.drawable.img_sub_logo
        }
        initGoogleLogin()

        lifecycleScope.launchWhenCreated {
            viewModel.googleLoginFlow.collectLatest { success ->
                if (success) {
                    Toast.makeText(requireContext(), "로그인 성공", Toast.LENGTH_SHORT).show()
                    passLogin()

                    /* TODO NewUser에 대해서 로그인 절차 추가 필요 */
                }
            }
        }
    }

    fun passLogin() {
        viewModel.navigateSelectTeam(requireActivity(), Intent.FLAG_ACTIVITY_CLEAR_TASK, Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    private fun initGoogleLogin() {
        startGoogleLoginForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == AppCompatActivity.RESULT_OK) {
                    result.data?.let { data ->
                        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                        try {
                            val account = task.getResult(ApiException::class.java)
                            Log.d(TAG, "googleLoginToken: ${account.idToken}")

                            if (account.idToken != null) {
                                Log.d(TAG, "viewModel 호출")
                                viewModel.getUserTokenInfo(account.idToken!!)
                            } else {
                                Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: ApiException) {
                            Log.e(TAG, "Google Result Error $result")
                        }
                    }
                } else {
                    Log.e(TAG, "initGoogleLogin: ${result.resultCode} ${result.data?.data} ")
                }
            }
    }

    fun googleLogin() {
        startGoogleLoginForResult.launch(viewModel.getGoogleSignInClient().signInIntent)
    }

    companion object {
        const val TAG = "LoginFragment"

        fun newInstance() = LoginFragment()
    }
}