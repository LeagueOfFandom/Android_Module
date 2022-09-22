package com.soma.lof.match.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.soma.common.base.BaseFragment
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchInfoBinding

class MatchInfoFragment : BaseFragment<FragmentMatchInfoBinding>(R.layout.fragment_match_info) {

    override fun initView() {

    }

    companion object {

        @JvmStatic
        fun newInstance() = MatchInfoFragment()
    }


}