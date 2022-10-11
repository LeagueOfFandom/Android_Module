package com.soma.lof.login.ui

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.activityViewModels
import com.soma.lof.foundation.base.BaseFragment
import com.soma.lof.login.R
import com.soma.lof.login.databinding.FragmentNickFirstSetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetFirstNickFragment : BaseFragment<FragmentNickFirstSetBinding>(R.layout.fragment_nick_first_set) {

    private val viewModel by activityViewModels<LoginViewModel>()

    override fun initView() {

        binding.nickInputField.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0 != null) {
                    binding.nicknameCompleteBtn.isEnabled = p0.isNotEmpty()
                }
            }
        })

        binding.nicknameCompleteBtn.setOnClickListener {
            viewModel.navigateSelectTeam(requireActivity(), Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }
}