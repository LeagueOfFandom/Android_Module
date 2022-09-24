package com.soma.lof.common.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.soma.lof.common.R
import com.soma.lof.common.util.NavigationFlow
import com.soma.lof.common.util.ToFlowNavigable

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as ToFlowNavigable).navigateToFlow(NavigationFlow.HomeFlow)
    }

    companion object {

        @JvmStatic
        fun newInstance() = StartFragment()
    }
}