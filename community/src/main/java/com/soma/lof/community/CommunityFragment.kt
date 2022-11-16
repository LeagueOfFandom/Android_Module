package com.soma.lof.community

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.soma.common.ui.base.BaseFragment
import com.soma.lof.community.databinding.FragmentCommunityBinding
import com.soma.lof.core.result.data
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

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

        lifecycleScope.launchWhenStarted {
            viewModel.communityData.collectLatest {
                postAdapter.submitList(it.data)
            }
        }
    }

    /** [CommunityFragmentListener] */
    override fun navigatePostWriteFragment() {
        findNavController().navigate(R.id.action_communityFragment_to_postWriteFragment)
    }
}

interface CommunityFragmentListener {
    fun navigatePostWriteFragment()
}