package com.soma.lof.match.ui

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.soma.common.base.BaseFragment
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchPredictionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchPredictionFragment() : BaseFragment<FragmentMatchPredictionBinding>(R.layout.fragment_match_prediction) {

    override fun initView() {

    }



//        if (viewModel.matchInfoSetData.value != null) {
//            updateChart()
//        }
//
//        viewModel.matchInfoSetData.observe(viewLifecycleOwner) {
//            updateChart()
//        }



    override fun onStart() {
        super.onStart()
//        if (viewModel.matchInfoSetData.value == null) {
//            viewModel.getMatchInfo(matchId)
//        }
    }

    private fun updateChart() {
//        val mainData = viewModel.matchInfoSetData.value?.mainInfo
//        val predictionData = viewModel.matchInfoSetData.value?.prediction
//        if (mainData != null && predictionData != null) {
            val entries = ArrayList<PieEntry>()

//            entries.add(PieEntry(predictionData.blueTeamWin.toFloat(), mainData.blueTeamName))
//            entries.add(PieEntry(predictionData.redTeamWin.toFloat(), mainData.redTeamName))

            binding.matchPredictionChart.animateY(1000, Easing.EaseInOutQuad);

            val pieDataSet = PieDataSet(entries, "")
            pieDataSet.sliceSpace = 3f
            pieDataSet.selectionShift = 5f

            val colors = ArrayList<Int>()
//            colors.add(getColor(Team.valueOf(mainData.blueTeamName).color))
//            colors.add(getColor(Team.valueOf(mainData.redTeamName).color))
            colors.add(Color.GREEN)
            colors.add(Color.RED)
            pieDataSet.colors = colors

            val pieData = PieData(pieDataSet).apply {
                setValueTextSize(11f)
                setValueTextColor(Color.WHITE)
            }
            pieData.setValueFormatter(PercentFormatter())

            binding.matchPredictionChart.apply {
                setUsePercentValues(true)
                centerText = "Prediction Rate"
                description.isEnabled = false
                setExtraOffsets(5f, 10f, 5f, 5f)
                dragDecelerationFrictionCoef = 0.95f
                isDrawHoleEnabled = true
                setHoleColor(Color.WHITE)
                isHighlightPerTapEnabled = true;
                data = pieData
                setEntryLabelTypeface(Typeface.DEFAULT_BOLD)
                highlightValues(null)
            }
        }
//    }

    companion object {
        fun newInstance(matchId: Long) : Fragment {
            val fragment = MatchPredictionFragment()
            val bundle = Bundle()
            bundle.putLong("MATCH_ID", matchId)
            fragment.arguments = bundle
            return fragment
        }
    }
}