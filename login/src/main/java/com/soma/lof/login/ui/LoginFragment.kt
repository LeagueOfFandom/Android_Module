package com.soma.lof.login.ui

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.soma.common.ui.base.BaseFragment
import com.soma.lof.core.result.data
import com.soma.lof.login.R
import com.soma.lof.login.databinding.FragmentLoginBinding
import com.soma.lof.login.util.LoginUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login),
    LoginFragmentListener {

    @Inject
    @Named("Main")
    lateinit var homeActivityClass: Class<*>

    private lateinit var startGoogleLoginForResult: ActivityResultLauncher<Intent>
    private val viewModel by viewModels<LoginViewModel>()
    private var account: GoogleSignInAccount? = null

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
        lifecycleScope.launchWhenStarted {
            viewModel.newUserFlow.collectLatest { isNewUser ->
                Timber.tag("check@@@").d("checkNewUser ${isNewUser} account: ${account}")
                if (account != null) {
                     if (isNewUser.data?.isNewUser != false) {
                        userLoginPolicyCheck(account!!.email ?: "", account!!.displayName ?: "", account!!.photoUrl?.toString() ?: "")
                    } else {
                        viewModel.getUserTokenInfo(account!!.email ?: "", account!!.displayName ?: "", account!!.photoUrl?.toString() ?: "")
                    }
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.googleLoginFlow.collectLatest { success ->
                Timber.tag("check@@@").d("googleLoginFlow ${success}")
                if (success) {
                    if (viewModel.newUserFlow.value.data?.isNewUser != false) {
                        navigateSetFirstNicknameFragment()
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
                            account = task.getResult(ApiException::class.java)
                            Timber.tag("check@@@").d("Google Login Click Success")
                            viewModel.checkNewUser(account!!.email ?: "")

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

    private fun navigateSetFirstNicknameFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_setFirstNickFragment)
    }

    /** [LoginFragmentListener] */
    override fun googleLogin() {
        startGoogleLoginForResult.launch(viewModel.getGoogleSignInClient().signInIntent)
    }

    private fun userLoginPolicyCheck(email: String, displayName: String, photoUrl: String) {
        val dialog = Dialog(requireContext())
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_login_policy)
        val params = dialog.window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.horizontalMargin = 30f
        dialog.show()

        val allCheckBox = dialog.findViewById<CheckBox>(R.id.login_policy_check_all)
        val requiredBox = dialog.findViewById<CheckBox>(R.id.login_policy_personal_required_checkbox)
        val optionalNicknameBox = dialog.findViewById<CheckBox>(R.id.login_policy_personal_optional_nickname_checkbox)
        val optionalPhotoBox = dialog.findViewById<CheckBox>(R.id.login_policy_personal_optional_photo_checkbox)

        dialog.findViewById<LinearLayout>(R.id.login_policy_accept_area).setOnClickListener {
            if (requiredBox.isChecked) {
                val userDisplayName = if (optionalNicknameBox.isChecked) displayName else ""
                val userPhotoUrl = if (optionalPhotoBox.isChecked) photoUrl else ""
                viewModel.getUserTokenInfo(email, userDisplayName, userPhotoUrl)
                dialog.dismiss()
            }
        }

        allCheckBox.setOnClickListener {
            requiredBox.isChecked = allCheckBox.isChecked
            optionalNicknameBox.isChecked = allCheckBox.isChecked
            optionalPhotoBox.isChecked = allCheckBox.isChecked
        }

        dialog.findViewById<TextView>(R.id.login_policy_personal_required_view_more).setOnClickListener {
            dialog.dismiss()
            val action = LoginFragmentDirections.actionLoginFragmentToRequiredUserCreatePolicyFragment(email)
            findNavController().navigate(action)
        }
    }

    companion object {
        const val TAG = "LoginFragment"
    }
}

interface LoginFragmentListener {
    fun googleLogin()
}