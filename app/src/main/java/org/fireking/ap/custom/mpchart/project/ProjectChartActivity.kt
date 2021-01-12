package org.fireking.ap.custom.mpchart.project

import android.content.Context
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
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

        //盈利预测
        initProfitForecast()

        //综合评分
        initComprehensiveScore()

        //筹码分布

        //未来走势

        //收益趋势
        initEarningsTrend()

        //资金流向
        initMoneyFlow()

        //主力净流出/入

        //行业主力资金
        initMainFunds()
    }

    private fun initMoneyFlow() {
        chart_money_flow.setBackgroundColor(Color.parseColor("#389393"))
        chart_money_flow.setNoDataText(null)
        chart_money_flow.animateX(500)
        chart_money_flow.description.isEnabled = false
        chart_money_flow.setScaleEnabled(false)

        chart_money_flow.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart_money_flow.xAxis.axisMinimum = -0.5F
        chart_money_flow.xAxis.mAxisMaximum = 4.5F
        chart_money_flow.xAxis.setDrawAxisLine(false)
        chart_money_flow.xAxis.setDrawGridLines(false)
        chart_money_flow.xAxis.setLabelCount(5, false)
        chart_money_flow.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return if (value == 4F) {
                    "${value.toInt() + 2016}(年)"
                } else {
                    "${value.toInt() + 2016}"
                }
            }
        }

        chart_money_flow.axisLeft.setDrawAxisLine(false)
        chart_money_flow.axisLeft.gridColor = Color.parseColor("#fbf6f0")
        chart_money_flow.axisLeft.axisLineColor = Color.parseColor("#fbf6f0")
        chart_money_flow.axisLeft.textColor = Color.parseColor("#fbf6f0")
        chart_money_flow.axisRight.isEnabled = false
        chart_money_flow.axisLeft.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "${value.toInt()}.00"
            }
        }

        chart_money_flow.xAxis.yOffset = 10F
        chart_money_flow.xAxis.textColor = Color.parseColor("#fbf6f0")
        chart_money_flow.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        chart_money_flow.legend.xEntrySpace = 10F
        chart_money_flow.legend.textColor = Color.parseColor("#fbf6f0")
        chart_money_flow.legend.form = Legend.LegendForm.CIRCLE
        chart_money_flow.legend.yOffset = 5F
        chart_money_flow.extraBottomOffset = 5F  //设置x轴底部和legend之间的间距
        val legendList = ArrayList<LegendEntry>()
        legendList.add(
            LegendEntry(
                "个股资金流出",
                Legend.LegendForm.CIRCLE,
                8F,
                0F,
                DashPathEffect(floatArrayOf(0F, 0F), 0F),
                Color.parseColor("#ff414d")
            )
        )
        legendList.add(
            LegendEntry(
                "行业资金流出",
                Legend.LegendForm.CIRCLE,
                8F,
                0F,
                DashPathEffect(floatArrayOf(0F, 0F), 0F),
                Color.parseColor("#32e0c4")
            )
        )
        legendList.add(
            LegendEntry(
                "个股资金流入",
                Legend.LegendForm.CIRCLE,
                8F,
                0F,
                DashPathEffect(floatArrayOf(0F, 0F), 0F),
                Color.parseColor("#f56a79")
            )
        )
        legendList.add(
            LegendEntry(
                "行业资金流入",
                Legend.LegendForm.CIRCLE,
                8F,
                0F,
                DashPathEffect(floatArrayOf(0F, 0F), 0F),
                Color.parseColor("#32e0c4")
            )
        )
        chart_money_flow.legend.setCustom(legendList)

        val oneDataList = ArrayList<BarEntry>()
        oneDataList.add(BarEntry(0F, -300F))
        oneDataList.add(BarEntry(1F, 350F))
        oneDataList.add(BarEntry(2F, -420F))
        oneDataList.add(BarEntry(3F, 620F))
        oneDataList.add(BarEntry(4F, 850F))
        val oneDataSet = SimpleBarDataSet(oneDataList, "预测值")
        oneDataSet.setDrawValues(false)
        oneDataSet.setColors(
            Color.parseColor("#ff414d"),
            Color.parseColor("#0d7377")
        )

        val twoDataList = ArrayList<BarEntry>()
        twoDataList.add(BarEntry(0F, -260F))
        twoDataList.add(BarEntry(1F, -300F))
        twoDataList.add(BarEntry(2F, -380F))
        twoDataList.add(BarEntry(3F, -500F))
        twoDataList.add(BarEntry(4F, 650F))
        val twoDataSet = SimpleBarDataSet(twoDataList, "实际值")
        twoDataSet.setDrawValues(false)
        twoDataSet.setColors(
            Color.parseColor("#f56a79"),
            Color.parseColor("#32e0c4")
        )
        val towBarData = ArrayList<IBarDataSet>()
        towBarData.add(oneDataSet)
        towBarData.add(twoDataSet)

        val barData = BarData(towBarData)
        barData.barWidth = 0.35F
        barData.groupBars(-0.5F, 0.3F, 0F)

        chart_money_flow.data = barData
        chart_money_flow.invalidate()
    }

    private fun initEarningsTrend() {
        chart_earnings_trend.setBackgroundColor(Color.parseColor("#ffa5a5"))
        chart_earnings_trend.setNoDataText(null)
        chart_earnings_trend.setScaleEnabled(false)
        chart_earnings_trend.description.isEnabled = false

        chart_earnings_trend.xAxis.yOffset = 10F
        chart_earnings_trend.xAxis.textColor = Color.parseColor("#fbf6f0")
        chart_earnings_trend.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        chart_earnings_trend.legend.xEntrySpace = 100F
        chart_earnings_trend.legend.textColor = Color.parseColor("#fbf6f0")
        chart_earnings_trend.legend.form = Legend.LegendForm.LINE
        chart_earnings_trend.legend.yOffset = 5F
        chart_earnings_trend.extraBottomOffset = 5F  //设置x轴底部和legend之间的间距

        chart_earnings_trend.axisLeft.gridColor = Color.parseColor("#fbf6f0")
        chart_earnings_trend.axisLeft.textColor = Color.parseColor("#fbf6f0")
        chart_earnings_trend.axisLeft.axisMinimum = -40F
        chart_earnings_trend.axisLeft.axisMaximum = 40F
        chart_earnings_trend.axisLeft.setDrawAxisLine(false)
        chart_earnings_trend.axisRight.isEnabled = false
        chart_earnings_trend.xAxis.setDrawGridLines(false)
        chart_earnings_trend.xAxis.axisMinimum = 0F
        chart_earnings_trend.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart_earnings_trend.xAxis.textColor = Color.parseColor("#fbf6f0")
        chart_earnings_trend.xAxis.axisLineColor = Color.parseColor("#fbf6f0")
        chart_earnings_trend.xAxis.setLabelCount(2, true)
        chart_earnings_trend.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return if (value == 0F) {
                    "09-12"
                } else {
                    "10-12"
                }
            }
        }

        val oneLineList = ArrayList<Entry>()
        val twoLineList = ArrayList<Entry>()
        val threeLineList = ArrayList<Entry>()
        for (index in 0 until 30) {
            val value = when {
                index <= 10 -> {
                    (Math.random() * 30).toFloat()
                }
                index in 11..20 -> {
                    -(Math.random() * 30).toFloat()
                }
                else -> {
                    (Math.random() * 30).toFloat()
                }
            }
            oneLineList.add(Entry(index.toFloat(), value))
            twoLineList.add(Entry(index.toFloat(), value + 10))
            threeLineList.add(Entry(index.toFloat(), value + 12))
        }

        val oneLineDataSet = LineDataSet(oneLineList, "个股累计")
        oneLineDataSet.color = Color.parseColor("#0f3057")
        oneLineDataSet.setDrawCircles(false)
        oneLineDataSet.setDrawValues(false)

        val twoLineDataSet = LineDataSet(twoLineList, "行业累计")
        twoLineDataSet.color = Color.parseColor("#03c4a1")
        twoLineDataSet.setDrawCircles(false)
        twoLineDataSet.setDrawValues(false)

        val threeLineDataSet = LineDataSet(threeLineList, "大盘累计")
        threeLineDataSet.color = Color.parseColor("#0278ae")
        threeLineDataSet.setDrawCircles(false)
        threeLineDataSet.setDrawValues(false)

        val lineData = LineData(oneLineDataSet, twoLineDataSet, threeLineDataSet)
        chart_earnings_trend.data = lineData
        chart_earnings_trend.invalidate()
    }

    private fun initMainFunds() {
        chart_main_funds.setBackgroundColor(Color.parseColor("#28abb9"))
        chart_main_funds.setNoDataText(null)
        chart_main_funds.animateX(500)
        chart_main_funds.description.isEnabled = false
        chart_main_funds.setScaleEnabled(false)

        chart_main_funds.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart_main_funds.xAxis.axisMinimum = -0.5F
        chart_main_funds.xAxis.mAxisMaximum = 4.5F
        chart_main_funds.xAxis.setDrawAxisLine(false)
        chart_main_funds.xAxis.setDrawGridLines(false)
        chart_main_funds.xAxis.setLabelCount(5, false)
        chart_main_funds.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return if (value == 4F) {
                    "${value.toInt() + 2016}(年)"
                } else {
                    "${value.toInt() + 2016}"
                }
            }
        }

        chart_main_funds.axisLeft.setDrawAxisLine(false)
        chart_main_funds.axisLeft.gridColor = Color.parseColor("#fbf6f0")
        chart_main_funds.axisLeft.axisLineColor = Color.parseColor("#fbf6f0")
        chart_main_funds.axisLeft.textColor = Color.parseColor("#fbf6f0")
        chart_main_funds.axisRight.isEnabled = false
        chart_main_funds.axisLeft.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "${value.toInt()}.00"
            }
        }

        chart_main_funds.xAxis.yOffset = 10F
        chart_main_funds.xAxis.textColor = Color.parseColor("#fbf6f0")
        chart_main_funds.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        chart_main_funds.legend.xEntrySpace = 10F
        chart_main_funds.legend.textColor = Color.parseColor("#fbf6f0")
        chart_main_funds.legend.form = Legend.LegendForm.CIRCLE
        chart_main_funds.legend.yOffset = 5F
        chart_main_funds.extraBottomOffset = 5F  //设置x轴底部和legend之间的间距
        val legendList = ArrayList<LegendEntry>()
        legendList.add(
            LegendEntry(
                "个股资金流出",
                Legend.LegendForm.CIRCLE,
                8F,
                0F,
                DashPathEffect(floatArrayOf(0F, 0F), 0F),
                Color.parseColor("#ff414d")
            )
        )
        legendList.add(
            LegendEntry(
                "行业资金流出",
                Legend.LegendForm.CIRCLE,
                8F,
                0F,
                DashPathEffect(floatArrayOf(0F, 0F), 0F),
                Color.parseColor("#32e0c4")
            )
        )
        legendList.add(
            LegendEntry(
                "个股资金流入",
                Legend.LegendForm.CIRCLE,
                8F,
                0F,
                DashPathEffect(floatArrayOf(0F, 0F), 0F),
                Color.parseColor("#f56a79")
            )
        )
        legendList.add(
            LegendEntry(
                "行业资金流入",
                Legend.LegendForm.CIRCLE,
                8F,
                0F,
                DashPathEffect(floatArrayOf(0F, 0F), 0F),
                Color.parseColor("#32e0c4")
            )
        )
        chart_main_funds.legend.setCustom(legendList)

        val oneDataList = ArrayList<BarEntry>()
        oneDataList.add(BarEntry(0F, -300F))
        oneDataList.add(BarEntry(1F, 350F))
        oneDataList.add(BarEntry(2F, -420F))
        oneDataList.add(BarEntry(3F, 620F))
        oneDataList.add(BarEntry(4F, 850F))
        val oneDataSet = SimpleBarDataSet(oneDataList, "预测值")
        oneDataSet.setDrawValues(false)
        oneDataSet.setColors(
            Color.parseColor("#ff414d"),
            Color.parseColor("#0d7377")
        )

        val twoDataList = ArrayList<BarEntry>()
        twoDataList.add(BarEntry(0F, -260F))
        twoDataList.add(BarEntry(1F, -300F))
        twoDataList.add(BarEntry(2F, -380F))
        twoDataList.add(BarEntry(3F, -500F))
        twoDataList.add(BarEntry(4F, 650F))
        val twoDataSet = SimpleBarDataSet(twoDataList, "实际值")
        twoDataSet.setDrawValues(false)
        twoDataSet.setColors(
            Color.parseColor("#f56a79"),
            Color.parseColor("#32e0c4")
        )

        val towBarData = ArrayList<IBarDataSet>()
        towBarData.add(oneDataSet)
        towBarData.add(twoDataSet)

        val barData = BarData(towBarData)
        barData.barWidth = 0.35F
        barData.groupBars(-0.5F, 0.3F, 0F)

        chart_main_funds.data = barData
        chart_main_funds.invalidate()
    }

    private fun initComprehensiveScore() {
        chart_comprehensive_score.setBackgroundColor(Color.parseColor("#ffa36c"))
        chart_comprehensive_score.setNoDataText(null)
        chart_comprehensive_score.description.isEnabled = false

        val radarDataEntityList = ArrayList<RadarEntry>()
        radarDataEntityList.add(RadarEntry(100F))
        radarDataEntityList.add(RadarEntry(75F))
        radarDataEntityList.add(RadarEntry(50F))
        radarDataEntityList.add(RadarEntry(80F))
        radarDataEntityList.add(RadarEntry(50F))

        val radarDataSet = RadarDataSet(radarDataEntityList, "")
        radarDataSet.setDrawFilled(true)
        radarDataSet.fillColor = Color.parseColor("#16a596")

        val radarData = RadarData(radarDataSet)
        radarData.setDrawValues(false)

        chart_comprehensive_score.webColor = Color.parseColor("#fbf6f0")
        chart_comprehensive_score.webColorInner = Color.parseColor("#fbf6f0")
        chart_comprehensive_score.yAxis.axisMinimum = 0F
        chart_comprehensive_score.xAxis.textColor = Color.parseColor("#fbf6f0")
        chart_comprehensive_score.yAxis.setLabelCount(5, false)
        chart_comprehensive_score.yAxis.setDrawLabels(false)
        val temp = arrayListOf("技术趋势", "价值评估", "交易机会", "舆情分析", "资金流向")
        chart_comprehensive_score.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return temp[(value % temp.size).toInt()]
            }
        }
        chart_comprehensive_score.legend.isEnabled = false

        chart_comprehensive_score.data = radarData
        chart_comprehensive_score.invalidate()
    }

    private fun initProfitForecast() {
        chart_profit_forecast.setBackgroundColor(Color.parseColor("#34626c"))
        chart_profit_forecast.setNoDataText(null)
        chart_profit_forecast.animateX(500)
        chart_profit_forecast.description.isEnabled = false
        chart_profit_forecast.setScaleEnabled(false)

        chart_profit_forecast.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart_profit_forecast.xAxis.axisMinimum = -0.5F
        chart_profit_forecast.xAxis.mAxisMaximum = 4.5F
        chart_profit_forecast.xAxis.setDrawGridLines(false)
        chart_profit_forecast.xAxis.setLabelCount(5, false)
        chart_profit_forecast.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return if (value == 4F) {
                    "${value.toInt() + 2016}(年)"
                } else {
                    "${value.toInt() + 2016}"
                }
            }
        }

        chart_profit_forecast.axisLeft.setDrawAxisLine(false)
        chart_profit_forecast.axisLeft.axisMinimum = 0F
        chart_profit_forecast.axisLeft.gridColor = Color.parseColor("#fbf6f0")
        chart_profit_forecast.axisLeft.axisLineColor = Color.parseColor("#fbf6f0")
        chart_profit_forecast.axisLeft.textColor = Color.parseColor("#fbf6f0")
        chart_profit_forecast.axisRight.isEnabled = false
        chart_profit_forecast.axisLeft.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "${value.toInt()}.00"
            }
        }

        chart_profit_forecast.xAxis.yOffset = 10F
        chart_profit_forecast.xAxis.textColor = Color.parseColor("#fbf6f0")
        chart_profit_forecast.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        chart_profit_forecast.legend.xEntrySpace = 100F
        chart_profit_forecast.legend.textColor = Color.parseColor("#fbf6f0")
        chart_profit_forecast.legend.form = Legend.LegendForm.CIRCLE
        chart_profit_forecast.legend.yOffset = 5F
        chart_profit_forecast.extraBottomOffset = 5F  //设置x轴底部和legend之间的间距

        val oneDataList = ArrayList<BarEntry>()
        oneDataList.add(BarEntry(0F, 300F))
        oneDataList.add(BarEntry(1F, 350F))
        oneDataList.add(BarEntry(2F, 420F))
        oneDataList.add(BarEntry(3F, 620F))
        oneDataList.add(BarEntry(4F, 850F))
        val oneDataSet = BarDataSet(oneDataList, "预测值")
        oneDataSet.setDrawValues(false)
        oneDataSet.color = Color.parseColor("#f05454")

        val twoDataList = ArrayList<BarEntry>()
        twoDataList.add(BarEntry(0F, 260F))
        twoDataList.add(BarEntry(1F, 300F))
        twoDataList.add(BarEntry(2F, 380F))
        twoDataList.add(BarEntry(3F, 500F))
        twoDataList.add(BarEntry(4F, 650F))
        val twoDataSet = BarDataSet(twoDataList, "实际值")
        twoDataSet.setDrawValues(false)
        twoDataSet.color = Color.parseColor("#8f384d")

        val towBarData = ArrayList<IBarDataSet>()
        towBarData.add(oneDataSet)
        towBarData.add(twoDataSet)

        val barData = BarData(towBarData)
        barData.barWidth = 0.35F
        barData.groupBars(-0.5F, 0.3F, 0F)

        chart_profit_forecast.data = barData
        chart_profit_forecast.invalidate()
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
        lineDataSet.setDrawCircles(false)
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
        chart_value_level.renderer.paintRender.strokeCap = Paint.Cap.ROUND
        chart_value_level.animateX(500)
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