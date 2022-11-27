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

        /* 새로운 유저라면 유저 정보 수집 정책 수락해야함 */
        lifecycleScope.launchWhenStarted {
            viewModel.newUserFlow.collectLatest { isNewUser ->
                if (account != null) {
                     if (isNewUser.data?.isNewUser == true) {
                        userLoginPolicyCheck(account!!.email ?: "", account!!.displayName ?: "", account!!.photoUrl?.toString() ?: "")
                    } else {
                        viewModel.getUserJwtToken(account!!.email ?: "", account!!.displayName ?: "", account!!.photoUrl?.toString() ?: "")
                    }
                }
            }
        }

        /* 새로운 유저라면 첫 닉네임 저장해야함 */
        lifecycleScope.launchWhenStarted {
            viewModel.googleLoginFlow.collectLatest { success ->
                if (success) {
                    if (viewModel.newUserFlow.value.data?.isNewUser == true) {
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

                            // 새로운 유저인지 아닌지 판별
                            viewModel.checkNewUser(account!!.email ?: "")

                        } catch (e: ApiException) {
                            shortShowToast("Data Google Result Code: ${result.resultCode}")
                        }
                    }
                } else {
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

        /* 유저정보수집정책 수락 시 유저 생성 및 jwtToken 가져옴 */
        dialog.findViewById<LinearLayout>(R.id.login_policy_accept_area).setOnClickListener {
            if (requiredBox.isChecked) {
                val userDisplayName = if (optionalNicknameBox.isChecked) displayName else ""
                val userPhotoUrl = if (optionalPhotoBox.isChecked) photoUrl else ""
                viewModel.getUserJwtToken(email, userDisplayName, userPhotoUrl)
                dialog.dismiss()
            }
        }

        /* 전체 동의 */
        allCheckBox.setOnClickListener {
            requiredBox.isChecked = allCheckBox.isChecked
            optionalNicknameBox.isChecked = allCheckBox.isChecked
            optionalPhotoBox.isChecked = allCheckBox.isChecked
        }

        /* 정책 더보기 항목 */
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