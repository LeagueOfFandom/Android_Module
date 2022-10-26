package com.soma.lof.setting

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.soma.common_ui.R.string.nick_text_cnt
import com.soma.common_ui.base.BaseFragment
import com.soma.lof.setting.databinding.FragmentProfileModifyBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ModifyProfileFragment : BaseFragment<FragmentProfileModifyBinding>(R.layout.fragment_profile_modify) {

    private val viewModel: ModifyProfileViewModel by viewModels()

    override fun initView() {

        bind {
            vm = viewModel
        }

        binding.modifyProfileNicknameInputField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0 != null) {
                    binding.modifyProfileBtn.isEnabled = p0.isNotEmpty()
                    binding.modifyProfileNicknameInputTextCnt.text = getString(nick_text_cnt, p0.length, 16)
                }
            }
        })

        binding.modifyProfileNicknameInputField.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    val imm = requireContext().getSystemService() as InputMethodManager?
                    imm?.hideSoftInputFromWindow(binding.modifyProfileNicknameInputField.windowToken, 0)
                    return true
                }
                return false
            }
        })

        binding.modifyProfileBtn.setOnClickListener {
            val userInputNickname = binding.modifyProfileNicknameInputField.text.toString()
            if (validationNickName(userInputNickname)) {
                viewModel.setNickName(userInputNickname)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.nickNameSetSuccess.collectLatest {
                if (it) {
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun validationNickName(nickName: String): Boolean {
        if (nickName.length < 3) {
            Toast.makeText(requireContext(), "3글자 이상을 입력하셔야 합니다.", Toast.LENGTH_SHORT).show()
            return false
        }
        val regex = "([0-9a-zA-Z])*".toRegex()
        val matchResult: MatchResult? = regex.matchEntire(nickName)
        if (matchResult == null) {
            Toast.makeText(requireContext(), "영어와 숫자로 이루어져야 합니다.", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}