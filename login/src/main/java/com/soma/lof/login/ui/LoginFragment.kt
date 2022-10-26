package com.soma.lof.login.ui

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.soma.common_ui.base.BaseFragment
import com.soma.lof.login.R
import com.soma.lof.login.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

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

                    if (viewModel.newUserFlow.value) {
                        viewModel.navigateHomeFragment(requireActivity(), Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    } else {
                        navigateSelectLanguageFragment()
                    }
                    Toast.makeText(requireContext(), "로그인 성공", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initGoogleLogin() {
        startGoogleLoginForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == AppCompatActivity.RESULT_OK) {
                    result.data?.let { data ->
                        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                        try {
                            val account = task.getResult(ApiException::class.java)
                            viewModel.getUserTokenInfo(account.email,
                                account.displayName ?: "",
                                account.photoUrl?.toString() ?: "")

                            Timber.tag(TAG)
                                .d("googleLoginToken: ${account.email} ${account.givenName} ${account.displayName} ${account.photoUrl}")

                        } catch (e: ApiException) {
                            Timber.tag(TAG).e("Google Result Error $result")
                        }
                    }
                } else {
                    Timber.tag(TAG).e("initGoogleLogin: ${result.resultCode} ${result.data?.data}")
                }
            }
    }

    fun googleLogin() {
        startGoogleLoginForResult.launch(viewModel.getGoogleSignInClient().signInIntent)
    }


    private fun navigateSelectLanguageFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_selectLanguageFragment)
    }

    companion object {
        const val TAG = "LoginFragment"

        fun newInstance() = LoginFragment()
    }
}