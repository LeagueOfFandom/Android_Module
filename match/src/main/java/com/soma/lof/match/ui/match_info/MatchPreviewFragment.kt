package com.soma.lof.match.ui.match_info

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.soma.common.base.BaseFragment
import com.soma.common_ui.presentation.CommonListAdapter
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchPreviewBinding
import com.soma.lof.match.ui.match_up.MatchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchPreviewFragment() : BaseFragment<FragmentMatchPreviewBinding>(R.layout.fragment_match_preview) {

    private val viewModel: MatchInfoViewModel by viewModels(ownerProducer = { requireParentFragment() })
    private lateinit var matchInfoPreviewAdapter: CommonListAdapter

    override fun initView() {
        matchInfoPreviewAdapter = CommonListAdapter(viewModel.matchInfo.value.setInfo)

        bind {
            adapter = matchInfoPreviewAdapter
        }
    }

//        matchInfoPreviewAdapter = MatchInfoPreviewAdapter(viewModel)
//        binding.matchPreviewRv.apply {
//            setHasFixedSize(true)
//            layoutManager = LinearLayoutManager(requireContext())
//            adapter = matchInfoPreviewAdapter
//            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
//        }

//        viewModel.matchInfoSetData.observe(viewLifecycleOwner) {
//            it?.let {
//                Log.d("MatchPreviewFragment", "onViewCreated: 변경")
//                matchInfoPreviewAdapter.notifyDataSetChanged()
//            }
//        }


    companion object {
        fun newInstance(): Fragment = MatchPreviewFragment()

    }
}