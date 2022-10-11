package com.soma.lof.setting

import com.soma.lof.foundation.base.BaseFragment
import com.soma.lof.setting.databinding.FragmentProfileModifyBinding

class ModifyProfileFragment : BaseFragment<FragmentProfileModifyBinding>(R.layout.fragment_profile_modify) {
    override fun initView() {
        binding.modifyProfileImage.setOnClickListener {

        }
    }
}