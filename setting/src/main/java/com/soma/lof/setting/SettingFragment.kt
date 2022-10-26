package com.soma.lof.setting

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.soma.common_ui.base.BaseFragment
import com.soma.lof.core_network.result.data
import com.soma.lof.setting.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {

    private val viewModel by viewModels<SettingViewModel>()

    override fun initView() {
        binding.settingProfileArea.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_modifyProfileFragment)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.settingData.collectLatest {
                binding.settingNickname.text = it.data?.userNickName ?: ""
                binding.settingAlarmMatchSwitch.isChecked = it.data?.userAlarmSetting ?: true

                binding.settingAlarmMatchSwitch.setOnCheckedChangeListener { _, isChecked ->
                    viewModel.postUserMatchAlarm(isChecked)
                }
            }
        }

        binding.settingLogoutArea.setOnClickListener {
            viewModel.signOut(requireActivity())
        }

        binding.settingTeamArea.setOnClickListener {
            viewModel.selectTeam(requireActivity())
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getSettingData()
    }
}