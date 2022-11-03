package com.soma.lof.login.ui

import androidx.navigation.fragment.findNavController
import com.soma.common.ui.base.BaseFragment
import com.soma.lof.login.R
import com.soma.lof.login.databinding.FragmentLanguageSelectBinding

class SelectLanguageFragment : BaseFragment<FragmentLanguageSelectBinding>(R.layout.fragment_language_select), SelectLanguageFragmentListener{

    override fun initView() {

        bind {
            listener = this@SelectLanguageFragment
        }

        binding.selectLanguageRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            binding.selectLanguageBtn.isEnabled = true
        }
    }

    /** [SelectLanguageFragmentListener] */
    override fun navigateSetNickName() {
        findNavController().navigate(R.id.action_selectLanguageFragment_to_setFirstNickFragment)
    }
}

interface SelectLanguageFragmentListener {
    fun navigateSetNickName()
}