package org.fireking.ap.custom.mpchart.v1_1

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.android.synthetic.main.activity_v1_1_chart.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class ProjectChartActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<ProjectChartActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_v1_1_chart)

        //价值水平 | 成长能力 | 偿债能力
        initValueLevel()

        //现金流 | 营运能力 | 盈利能力
        initCashFlow()
    }

    private fun initCashFlow() {
        chart_cash_flow.setBackgroundColor(Color.parseColor("#59886b"))
        chart_cash_flow.setNoDataText(null)
        chart_cash_flow.drawOrder = arrayOf(
            CombinedChart.DrawOrder.BAR,
            CombinedChart.DrawOrder.LINE
        )
        chart_cash_flow.xAxis.setDrawGridLines(false)
        chart_cash_flow.axisLeft.setDrawGridLines(false)
        chart_cash_flow.animateX(400)
        chart_cash_flow.description.isEnabled = false
        chart_cash_flow.setScaleEnabled(false)
        chart_cash_flow.axisLeft.gridColor = Color.parseColor("#fbf6f0")
        chart_cash_flow.axisLeft.axisLineColor = Color.parseColor("#fbf6f0")
        chart_cash_flow.xAxis.axisMinimum = -0.5F
        chart_cash_flow.xAxis.axisMaximum = 4.5F
        chart_cash_flow.axisLeft.axisMinimum = 0F
        chart_cash_flow.axisLeft.axisMaximum = 80F
        chart_cash_flow.axisRight.isEnabled = false
        chart_cash_flow.xAxis.setDrawGridLines(false)
        chart_cash_flow.axisLeft.setDrawLabels(false)
        chart_cash_flow.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart_cash_flow.xAxis.gridColor = Color.parseColor("#fbf6f0")
        chart_cash_flow.xAxis.axisLineColor = Color.parseColor("#fbf6f0")
        chart_cash_flow.xAxis.setLabelCount(5, false)
        chart_cash_flow.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "${2016 + value.toInt()}年"
            }
        }
        chart_cash_flow.xAxis.yOffset = 10F
        chart_cash_flow.xAxis.textColor = Color.parseColor("#fbf6f0")
        chart_cash_flow.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        chart_cash_flow.legend.xEntrySpace = 100F
        chart_cash_flow.legend.textColor = Color.parseColor("#fbf6f0")
        chart_cash_flow.legend.form = Legend.LegendForm.CIRCLE
        chart_cash_flow.legend.yOffset = 5F
        chart_cash_flow.extraBottomOffset = 5F  //设置x轴底部和legend之间的间距

        val barDataList = ArrayList<BarEntry>()
        barDataList.add(BarEntry(0F, 55F))
        barDataList.add(BarEntry(1F, 45F))
        barDataList.add(BarEntry(2F, 78F))
        barDataList.add(BarEntry(3F, 67F))
        barDataList.add(BarEntry(4F, 76F))
        val barDataSet = BarDataSet(barDataList, "经营现金流净额")
        barDataSet.color = Color.parseColor("#fdcfdf")
        barDataSet.setDrawValues(false)
        val barData = BarData(barDataSet)
        barData.barWidth = 0.45F

        val lineDataList = ArrayList<Entry>()
        lineDataList.add(Entry(0F, 55F))
        lineDataList.add(Entry(1F, 45F))
        lineDataList.add(Entry(2F, 78F))
        lineDataList.add(Entry(3F, 67F))
        lineDataList.add(Entry(4F, 76F))
        val lineDataSet = LineDataSet(lineDataList, "经营现金流净额/营收")
        lineDataSet.color = Color.parseColor("#9ad3bc")
        lineDataSet.setDrawValues(false)
        val lineData = LineData(lineDataSet)

        val combinedData = CombinedData()
        combinedData.setData(lineData)
        combinedData.setData(barData)
        chart_cash_flow.data = combinedData
        chart_cash_flow.invalidate()
    }

    private fun initValueLevel() {
        chart_value_level.setBackgroundColor(Color.parseColor("#ec5858"))
        chart_value_level.setNoDataText(null)
        chart_value_level.setScaleEnabled(false)
        chart_value_level.axisLeft.axisMinimum = 0F
        chart_value_level.axisLeft.setDrawAxisLine(true)
        chart_value_level.axisLeft.setDrawGridLines(false)
        chart_value_level.axisLeft.axisMaximum = 70F
        chart_value_level.axisRight.isEnabled = false
        chart_value_level.axisLeft.gridColor = Color.parseColor("#fbf6f0")
        chart_value_level.axisLeft.axisLineColor = Color.parseColor("#fbf6f0")
        chart_value_level.axisLeft.setDrawLabels(false)
        chart_value_level.description.isEnabled = false
        chart_value_level.xAxis.gridColor = Color.parseColor("#fbf6f0")
        chart_value_level.xAxis.axisLineColor = Color.parseColor("#fbf6f0")
        chart_value_level.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart_value_level.xAxis.setDrawLabels(true)
        chart_value_level.xAxis.setDrawGridLines(false)
        chart_value_level.xAxis.axisMinimum = -0.5F
        chart_value_level.xAxis.axisMaximum = 19.5F
        chart_value_level.xAxis.setLabelCount(5, false)
        chart_value_level.xAxis.textColor = Color.parseColor("#fbf6f0")
        chart_value_level.xAxis.yOffset = 10F
        chart_value_level.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "${(value + 2016).toInt()}年"
            }
        }
        chart_value_level.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        chart_value_level.legend.xEntrySpace = 100F
        chart_value_level.legend.textColor = Color.parseColor("#fbf6f0")
        chart_value_level.legend.form = Legend.LegendForm.CIRCLE
        chart_value_level.legend.yOffset = 5F
        chart_value_level.extraBottomOffset = 5F  //设置x轴底部和legend之间的间距

        //设置数据
        val lineOneDataList = ArrayList<Entry>()
        for (index in 0 until 20) {
            lineOneDataList.add(Entry(index.toFloat(), (Math.random() * 12 + 50F).toFloat()))
        }
        val lineOneDataSet = LineDataSet(lineOneDataList, "市营率TTM")
        lineOneDataSet.color = Color.parseColor("#fff8c1")
        lineOneDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        lineOneDataSet.setDrawCircles(false)
        lineOneDataSet.setDrawValues(false)

        val lineTwoDataList = ArrayList<Entry>()
        for (index in 0 until 20) {
            lineTwoDataList.add(Entry(index.toFloat(), (Math.random() * 15 + 40F).toFloat()))
        }
        val lineTwoDataSet = LineDataSet(lineTwoDataList, "行业均值")
        lineTwoDataSet.color = Color.parseColor("#fbf6f0")
        lineTwoDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        lineTwoDataSet.setDrawCircles(false)
        lineTwoDataSet.setDrawValues(false)

        val lineData = LineData()
        lineData.addDataSet(lineOneDataSet)
        lineData.addDataSet(lineTwoDataSet)
        chart_value_level.data = lineData
        chart_value_level.invalidate()
    }
}