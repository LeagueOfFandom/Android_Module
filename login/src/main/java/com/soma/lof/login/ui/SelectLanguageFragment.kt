package com.soma.lof.login.ui

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.soma.common_ui.base.BaseFragment
import com.soma.lof.login.R
import com.soma.lof.login.databinding.FragmentLanguageSelectBinding

class SelectLanguageFragment : BaseFragment<FragmentLanguageSelectBinding>(R.layout.fragment_language_select){

    override fun initView() {

        binding.selectLanguageRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            binding.selectLanguageBtn.isEnabled = true
        }

        binding.selectLanguageBtn.setOnClickListener {
            findNavController().navigate(R.id.action_selectLanguageFragment_to_setFirstNickFragment)
        }
    }

    companion object {
        const val TAG = "SelectLanguageFragment"

        fun newInstance() : Fragment = SelectLanguageFragment()
    }
}