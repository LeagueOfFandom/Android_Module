package com.soma.lof.login.ui

import android.content.Intent
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
import com.soma.common.ui.base.BaseFragment
import com.soma.lof.login.R
import com.soma.lof.login.databinding.FragmentLoginBinding
import com.soma.lof.login.util.LoginUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login), LoginFragmentListener {

    @Inject
    @Named("Main")
    lateinit var homeActivityClass: Class<*>

    private lateinit var startGoogleLoginForResult: ActivityResultLauncher<Intent>
    private val viewModel by viewModels<LoginViewModel>()

    override fun initView() {

        bind {
            listener = this@LoginFragment
            logoResId = R.drawable.img_logo
            subLogoResId = R.drawable.img_sub_logo
        }

        initGoogleLogin()
        subscribeUI()
    }

    private fun subscribeUI() {
        lifecycleScope.launchWhenCreated {
            viewModel.googleLoginFlow.collectLatest { success ->
                if (success) {
                    if (viewModel.newUserFlow.value) {
                        navigateSelectLanguageFragment()
                    } else {
                        LoginUtil.startMainActivity(requireActivity(), homeActivityClass)
                    }
                    Toast.makeText(requireContext(), R.string.login_success, Toast.LENGTH_SHORT).show()
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

                        } catch (e: ApiException) {
                            Timber.tag(TAG).e("Google Result Error $result")
                            shortShowToast("Data Google Result Code: ${result.resultCode}")
                        }
                    }
                } else {
                    Timber.tag(TAG).e("initGoogleLogin: ${result.resultCode} ${result.data?.data}")
                    shortShowToast("Google Result Code: ${result.resultCode}")
                }
            }
    }

    private fun navigateSelectLanguageFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_selectLanguageFragment)
    }

    /** [LoginFragmentListener] */
    override fun googleLogin() {
        startGoogleLoginForResult.launch(viewModel.getGoogleSignInClient().signInIntent)
    }

    companion object {
        const val TAG = "LoginFragment"
    }
}

interface LoginFragmentListener {
    fun googleLogin()
}