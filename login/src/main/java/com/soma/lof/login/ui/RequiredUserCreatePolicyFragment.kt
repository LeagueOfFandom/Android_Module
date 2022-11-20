package com.soma.lof.login.ui

import androidx.navigation.fragment.navArgs
import com.soma.common.ui.base.BaseFragment
import com.soma.lof.login.R
import androidx.navigation.fragment.navArgs
import com.soma.lof.login.databinding.FragmentRequiredUserCreatePolicyBinding

class RequiredUserCreatePolicyFragment : BaseFragment<FragmentRequiredUserCreatePolicyBinding>(R.layout.fragment_required_user_create_policy) {

    val args: RequiredUserCreatePolicyFragmentArgs by navArgs()

    override fun initView() {
        binding.requiredUserCreatePolicyMsg.text = getString(R.string.required_user_create_policy_msg, args.email)
    }
}