package com.soma.lof.match.ui.match_info

import android.graphics.Color
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.soma.common.ui.base.BaseFragment
import com.soma.lof.core.model.entity.Team
import com.soma.lof.match.R
import com.soma.lof.match.databinding.FragmentMatchPredictionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchPredictionFragment() :
    BaseFragment<FragmentMatchPredictionBinding>(R.layout.fragment_match_prediction) {

    private val viewModel: MatchInfoViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun initView() {
        updateChart()
    }

    private fun updateChart() {

        val mainData = viewModel.matchDetailSetInfo.value?.teamVsTeamMainInfo
        val predictionData = viewModel.matchInfo.value.prediction
        val entries = ArrayList<PieEntry>()

        entries.add(PieEntry(predictionData.blueTeamWin.toFloat(), mainData?.blueTeamAcronym))
        entries.add(PieEntry(predictionData.redTeamWin.toFloat(), mainData?.redTeamAcronym))

        binding.matchPredictionChart.animateY(1000, Easing.EaseInOutQuad)

        val pieDataSet = PieDataSet(entries, "")
        pieDataSet.sliceSpace = 3f
        pieDataSet.selectionShift = 5f

        val colors = ArrayList<Int>()
        colors.add(Color.parseColor("#426BFF"))
        colors.add(Color.parseColor("#FC7B7B"))
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
            isHighlightPerTapEnabled = true
            data = pieData
            setEntryLabelTypeface(Typeface.DEFAULT_BOLD)
            highlightValues(null)
        }
    }

    companion object {
        fun newInstance() = MatchPredictionFragment()
    }
}