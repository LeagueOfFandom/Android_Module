package com.soma.lof.setting

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.soma.common.ui.base.BaseFragment
import com.soma.common.ui.util.MainActivityUtil
import com.soma.lof.core.result.data
import com.soma.lof.setting.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting), SettingFragmentListener {

    private val viewModel by viewModels<SettingViewModel>()

    override fun initView() {

        bind {
            vm = viewModel
            listener = this@SettingFragment
        }

        subscribeUI()
    }

    private fun subscribeUI() {
        lifecycleScope.launchWhenStarted {

            // SettingFragment Data - User Nickname, Alarm Setting, etc..
            viewModel.settingData.collectLatest {
                binding.settingNickname.text = it.data?.userNickName ?: ""
                binding.settingAlarmMatchSwitch.isChecked = it.data?.userAlarmSetting ?: true

                binding.settingAlarmMatchSwitch.setOnCheckedChangeListener { _, isChecked ->
                    viewModel.postUserMatchAlarm(isChecked)
                }
            }
        }

        // Logout
        lifecycleScope.launchWhenStarted {
            viewModel.signOutFlow.collectLatest {
                viewModel.signOutFlow.collectLatest { isSignOut ->
                    if (isSignOut) {
                        (activity as MainActivityUtil).startLoginActivity()
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getSettingData()
    }

    /** [SettingFragmentListener] */
    override fun navigateModifyFragment() {
        findNavController().navigate(R.id.action_settingFragment_to_modifyProfileFragment)
    }
}

interface SettingFragmentListener {
    fun navigateModifyFragment()
}