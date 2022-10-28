package com.soma.lof.community

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.soma.common.ui.base.BaseFragment
import com.soma.lof.community.databinding.FragmentCommunityBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CommunityFragment : BaseFragment<FragmentCommunityBinding>(R.layout.fragment_community) {

    private val viewModel by viewModels<CommunityViewModel>()
    private lateinit var postAdapter: PostAdapter

    override fun initView() {
        postAdapter = PostAdapter()
        binding.communityRv.addItemDecoration(PostItemDecoration())
        bind {
            adapter = postAdapter
        }

        postAdapter.submitList(viewModel.dummy)

        binding.communityPostWriteBtn.setOnClickListener {
            navigatePostWriteFragment()
        }
    }

    fun navigatePostWriteFragment() {
        findNavController().navigate(R.id.action_communityFragment_to_postWriteFragment)
    }

    companion object {
        fun newInstance() = CommunityFragment()
    }
}