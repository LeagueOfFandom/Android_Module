package com.soma.lof.community

import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
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
        val bottomSheetView = layoutInflater.inflate(R.layout.item_post_bottom_sheet, null)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(bottomSheetView)

        postAdapter = PostAdapter {
            val postBtmSheet = BottomSheetDialog(requireContext())

            val bottomSheetView = LayoutInflater.from(context).inflate(
                R.layout.item_post_bottom_sheet, null)


            bottomSheetView.findViewById<TextView>(R.id.item_post_user_block).setOnClickListener {
                Toast.makeText(requireContext(), R.string.user_block_tost, Toast.LENGTH_SHORT).show()
                postBtmSheet.dismiss()
            }

            bottomSheetView.findViewById<TextView>(R.id.item_post_report).setOnClickListener {
                Toast.makeText(requireContext(), R.string.post_report_toast, Toast.LENGTH_SHORT).show()
                postBtmSheet.dismiss()
            }

            postBtmSheet.setContentView(bottomSheetView)
            postBtmSheet.show()
        }

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