package com.soma.lof.community

import androidx.navigation.fragment.findNavController
import com.soma.common.ui.base.BaseFragment
import com.soma.lof.community.databinding.FragmentPostWriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostWriteFragment : BaseFragment<FragmentPostWriteBinding>(R.layout.fragment_post_write) {

    override fun initView() {

        binding.writePostBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}