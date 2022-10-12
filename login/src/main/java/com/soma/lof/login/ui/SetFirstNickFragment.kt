package com.soma.lof.login.ui

import android.content.Context.INPUT_METHOD_SERVICE
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.soma.lof.foundation.base.BaseFragment
import com.soma.lof.login.R
import com.soma.lof.login.databinding.FragmentNickFirstSetBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class SetFirstNickFragment :
    BaseFragment<FragmentNickFirstSetBinding>(R.layout.fragment_nick_first_set) {

    private val viewModel by viewModels<SetNickNameViewModel>()

    override fun initView() {

        binding.nickInputField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0 != null) {
                    binding.nicknameCompleteBtn.isEnabled = p0.isNotEmpty()
                    binding.nickInputTextCnt.text = getString(R.string.nick_text_cnt, p0.length, 16)
                }
            }
        })

        binding.nickInputField.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    val imm = requireContext().getSystemService() as InputMethodManager?
                    imm?.hideSoftInputFromWindow(binding.nickInputField.windowToken, 0)
                    return true
                }
                return false
            }
        });

        binding.nicknameCompleteBtn.setOnClickListener {
            Log.d("SetFirstNickFragment", "initView: 클릭 ${binding.nickInputField.text.toString()}")
            viewModel.setNickName(binding.nickInputField.text.toString())
        }

        lifecycleScope.launchWhenStarted {
            viewModel.nickNameSetSuccess.collectLatest {
                if (it) {
                    // viewModel.navigateSelectTeam(reqeActivity(), Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                }
            }
            viewModel.nick.collectLatest {
                Log.d("SetFirstNickFragment", "nickname: $it")
            }
        }
    }
}