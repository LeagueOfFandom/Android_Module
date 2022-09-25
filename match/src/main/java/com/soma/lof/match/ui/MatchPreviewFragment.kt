package com.soma.lof.match.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.soma.common.base.BaseFragment
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchPreviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchPreviewFragment() : BaseFragment<FragmentMatchPreviewBinding>(R.layout.fragment_match_preview) {

//    private val viewModel by viewModels<MatchInfoViewModel>(ownerProducer = { requireParentFragment() })
//    private lateinit var matchInfoPreviewAdapter: MatchInfoPreviewAdapter

    override fun initView() {

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


    override fun onStart() {
        super.onStart()
//        if (viewModel.matchInfoSetData.value == null) {
//            viewModel.getMatchInfo(matchId)
//        }
    }

    companion object {
        fun newInstance(matchId: Long) : Fragment {
            val fragment = MatchPreviewFragment()
            val bundle = Bundle()
            bundle.putLong("MATCH_ID", matchId)
            fragment.arguments = bundle
            return fragment
        }
    }
}