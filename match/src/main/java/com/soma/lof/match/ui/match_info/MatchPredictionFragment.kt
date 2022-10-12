package com.soma.lof.match.ui.match_info

import android.graphics.Color
import android.graphics.Typeface
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.soma.lof.core_model.entity.Team
import com.soma.lof.foundation.base.BaseFragment
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

        val mainData = viewModel.matchInfo.value.mainInfo
        val predictionData = viewModel.matchInfo.value.prediction
        val entries = ArrayList<PieEntry>()

        entries.add(PieEntry(predictionData.blueTeamWin.toFloat(), mainData.homeName))
        entries.add(PieEntry(predictionData.redTeamWin.toFloat(), mainData.awayName))

        binding.matchPredictionChart.animateY(1000, Easing.EaseInOutQuad);

        val pieDataSet = PieDataSet(entries, "")
        pieDataSet.sliceSpace = 3f
        pieDataSet.selectionShift = 5f

        val colors = ArrayList<Int>()
        colors.add(Color.parseColor(Team.valueOf(mainData.homeName ?: "T1").colorRGB))
        colors.add(Color.parseColor(Team.valueOf(mainData.awayName ?: "T1").colorRGB))
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

    companion object {
        fun newInstance() = MatchPredictionFragment()
    }
}