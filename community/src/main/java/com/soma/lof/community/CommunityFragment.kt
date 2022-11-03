package com.soma.lof.community

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.soma.common.ui.base.BaseFragment
import com.soma.lof.community.databinding.FragmentCommunityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommunityFragment : BaseFragment<FragmentCommunityBinding>(R.layout.fragment_community), CommunityFragmentListener {

    private val viewModel by viewModels<CommunityViewModel>()
    private lateinit var postAdapter: PostAdapter

    override fun initView() {
        postAdapter = PostAdapter()

        bind {
            listener = this@CommunityFragment
            adapter = postAdapter
            itemDecoration = PostItemDecoration()
        }

        // 서버 연결 시 삭제 예정
        postAdapter.submitList(viewModel.dummy)
    }

    /** [CommunityFragmentListener] */
    override fun navigatePostWriteFragment() {
        findNavController().navigate(R.id.action_communityFragment_to_postWriteFragment)
    }
}

interface CommunityFragmentListener {
    fun navigatePostWriteFragment()
}