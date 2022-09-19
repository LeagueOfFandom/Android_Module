package com.soma.lof.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.soma.common.base.BaseFragment
import com.soma.lof.info.databinding.FragmentInfoBinding

class InfoFragment : BaseFragment<FragmentInfoBinding>(R.layout.fragment_info) {

    override fun initView() {
    }

    companion object {
        fun newInstance() = InfoFragment()
    }

}