package com.soma.lof.setting

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.soma.lof.foundation.base.BaseFragment
import com.soma.lof.setting.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {

    private val viewModel by viewModels<SettingViewModel>()

    override fun initView() {
        binding.settingProfileArea.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_modifyProfileFragment)
        }

        binding.settingLogoutArea.setOnClickListener {
            viewModel.signOut(requireActivity())
        }
    }

    companion object {
        fun newInstance() = SettingFragment()
    }

}