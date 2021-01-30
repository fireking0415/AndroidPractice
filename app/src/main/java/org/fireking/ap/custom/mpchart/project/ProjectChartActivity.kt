package org.fireking.ap.custom.mpchart.project

import android.content.Context
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import org.fireking.ap.databinding.ActivityV11ChartBinding
import org.fireking.library.kotlin.ext.intentFor


class ProjectChartActivity : AppCompatActivity() {

    private var viewBinding: ActivityV11ChartBinding? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<ProjectChartActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityV11ChartBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

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
        viewBinding?.chartMoneyFlow?.setBackgroundColor(Color.parseColor("#389393"))
        viewBinding?.chartMoneyFlow?.setNoDataText(null)
        viewBinding?.chartMoneyFlow?.animateX(500)
        viewBinding?.chartMoneyFlow?.description?.isEnabled = false
        viewBinding?.chartMoneyFlow?.setScaleEnabled(false)

        viewBinding?.chartMoneyFlow?.xAxis?.position = XAxis.XAxisPosition.BOTTOM
        viewBinding?.chartMoneyFlow?.xAxis?.axisMinimum = -0.5F
        viewBinding?.chartMoneyFlow?.xAxis?.mAxisMaximum = 4.5F
        viewBinding?.chartMoneyFlow?.xAxis?.setDrawAxisLine(false)
        viewBinding?.chartMoneyFlow?.xAxis?.setDrawGridLines(false)
        viewBinding?.chartMoneyFlow?.xAxis?.setLabelCount(5, false)
        viewBinding?.chartMoneyFlow?.xAxis?.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return if (value == 4F) {
                    "${value.toInt() + 2016}(年)"
                } else {
                    "${value.toInt() + 2016}"
                }
            }
        }

        viewBinding?.chartMoneyFlow?.axisLeft?.setDrawAxisLine(false)
        viewBinding?.chartMoneyFlow?.axisLeft?.gridColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartMoneyFlow?.axisLeft?.axisLineColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartMoneyFlow?.axisLeft?.textColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartMoneyFlow?.axisRight?.isEnabled = false
        viewBinding?.chartMoneyFlow?.axisLeft?.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "${value.toInt()}.00"
            }
        }

        viewBinding?.chartMoneyFlow?.xAxis?.yOffset = 10F
        viewBinding?.chartMoneyFlow?.xAxis?.textColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartMoneyFlow?.legend?.horizontalAlignment =
            Legend.LegendHorizontalAlignment.CENTER
        viewBinding?.chartMoneyFlow?.legend?.xEntrySpace = 10F
        viewBinding?.chartMoneyFlow?.legend?.textColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartMoneyFlow?.legend?.form = Legend.LegendForm.CIRCLE
        viewBinding?.chartMoneyFlow?.legend?.yOffset = 5F
        viewBinding?.chartMoneyFlow?.extraBottomOffset = 5F  //设置x轴底部和legend之间的间距
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
        viewBinding?.chartMoneyFlow?.legend?.setCustom(legendList)

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

        viewBinding?.chartMoneyFlow?.data = barData
        viewBinding?.chartMoneyFlow?.invalidate()
    }

    private fun initEarningsTrend() {
        viewBinding?.chartEarningsTrend?.setBackgroundColor(Color.parseColor("#ffa5a5"))
        viewBinding?.chartEarningsTrend?.setNoDataText(null)
        viewBinding?.chartEarningsTrend?.setScaleEnabled(false)
        viewBinding?.chartEarningsTrend?.description?.isEnabled = false

        viewBinding?.chartEarningsTrend?.xAxis?.yOffset = 10F
        viewBinding?.chartEarningsTrend?.xAxis?.textColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartEarningsTrend?.legend?.horizontalAlignment =
            Legend.LegendHorizontalAlignment.CENTER
        viewBinding?.chartEarningsTrend?.legend?.xEntrySpace = 100F
        viewBinding?.chartEarningsTrend?.legend?.textColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartEarningsTrend?.legend?.form = Legend.LegendForm.LINE
        viewBinding?.chartEarningsTrend?.legend?.yOffset = 5F
        viewBinding?.chartEarningsTrend?.extraBottomOffset = 5F  //设置x轴底部和legend之间的间距

        viewBinding?.chartEarningsTrend?.axisLeft?.gridColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartEarningsTrend?.axisLeft?.textColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartEarningsTrend?.axisLeft?.axisMinimum = -40F
        viewBinding?.chartEarningsTrend?.axisLeft?.axisMaximum = 40F
        viewBinding?.chartEarningsTrend?.axisLeft?.setDrawAxisLine(false)
        viewBinding?.chartEarningsTrend?.axisRight?.isEnabled = false
        viewBinding?.chartEarningsTrend?.xAxis?.setDrawGridLines(false)
        viewBinding?.chartEarningsTrend?.xAxis?.axisMinimum = 0F
        viewBinding?.chartEarningsTrend?.xAxis?.position = XAxis.XAxisPosition.BOTTOM
        viewBinding?.chartEarningsTrend?.xAxis?.textColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartEarningsTrend?.xAxis?.axisLineColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartEarningsTrend?.xAxis?.setLabelCount(2, true)
        viewBinding?.chartEarningsTrend?.xAxis?.valueFormatter = object : ValueFormatter() {
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
        viewBinding?.chartEarningsTrend?.data = lineData
        viewBinding?.chartEarningsTrend?.invalidate()
    }

    private fun initMainFunds() {
        viewBinding?.chartMainFunds?.setBackgroundColor(Color.parseColor("#28abb9"))
        viewBinding?.chartMainFunds?.setNoDataText(null)
        viewBinding?.chartMainFunds?.animateX(500)
        viewBinding?.chartMainFunds?.description?.isEnabled = false
        viewBinding?.chartMainFunds?.setScaleEnabled(false)

        viewBinding?.chartMainFunds?.xAxis?.position = XAxis.XAxisPosition.BOTTOM
        viewBinding?.chartMainFunds?.xAxis?.axisMinimum = -0.5F
        viewBinding?.chartMainFunds?.xAxis?.mAxisMaximum = 4.5F
        viewBinding?.chartMainFunds?.xAxis?.setDrawAxisLine(false)
        viewBinding?.chartMainFunds?.xAxis?.setDrawGridLines(false)
        viewBinding?.chartMainFunds?.xAxis?.setLabelCount(5, false)
        viewBinding?.chartMainFunds?.xAxis?.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return if (value == 4F) {
                    "${value.toInt() + 2016}(年)"
                } else {
                    "${value.toInt() + 2016}"
                }
            }
        }

        viewBinding?.chartMainFunds?.axisLeft?.setDrawAxisLine(false)
        viewBinding?.chartMainFunds?.axisLeft?.gridColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartMainFunds?.axisLeft?.axisLineColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartMainFunds?.axisLeft?.textColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartMainFunds?.axisRight?.isEnabled = false
        viewBinding?.chartMainFunds?.axisLeft?.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "${value.toInt()}.00"
            }
        }

        viewBinding?.chartMainFunds?.xAxis?.yOffset = 10F
        viewBinding?.chartMainFunds?.xAxis?.textColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartMainFunds?.legend?.horizontalAlignment =
            Legend.LegendHorizontalAlignment.CENTER
        viewBinding?.chartMainFunds?.legend?.xEntrySpace = 10F
        viewBinding?.chartMainFunds?.legend?.textColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartMainFunds?.legend?.form = Legend.LegendForm.CIRCLE
        viewBinding?.chartMainFunds?.legend?.yOffset = 5F
        viewBinding?.chartMainFunds?.extraBottomOffset = 5F  //设置x轴底部和legend之间的间距
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
        viewBinding?.chartMainFunds?.legend?.setCustom(legendList)

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

        viewBinding?.chartMainFunds?.data = barData
        viewBinding?.chartMainFunds?.invalidate()
    }

    private fun initComprehensiveScore() {
        viewBinding?.chartComprehensiveScore?.setBackgroundColor(Color.parseColor("#ffa36c"))
        viewBinding?.chartComprehensiveScore?.setNoDataText(null)
        viewBinding?.chartComprehensiveScore?.description?.isEnabled = false

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

        viewBinding?.chartComprehensiveScore?.webColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartComprehensiveScore?.webColorInner = Color.parseColor("#fbf6f0")
        viewBinding?.chartComprehensiveScore?.yAxis?.axisMinimum = 0F
        viewBinding?.chartComprehensiveScore?.xAxis?.textColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartComprehensiveScore?.yAxis?.setLabelCount(5, false)
        viewBinding?.chartComprehensiveScore?.yAxis?.setDrawLabels(false)
        val temp = arrayListOf("技术趋势", "价值评估", "交易机会", "舆情分析", "资金流向")
        viewBinding?.chartComprehensiveScore?.xAxis?.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return temp[(value % temp.size).toInt()]
            }
        }
        viewBinding?.chartComprehensiveScore?.legend?.isEnabled = false

        viewBinding?.chartComprehensiveScore?.data = radarData
        viewBinding?.chartComprehensiveScore?.invalidate()
    }

    private fun initProfitForecast() {
        viewBinding?.chartProfitForecast?.setBackgroundColor(Color.parseColor("#34626c"))
        viewBinding?.chartProfitForecast?.setNoDataText(null)
        viewBinding?.chartProfitForecast?.animateX(500)
        viewBinding?.chartProfitForecast?.description?.isEnabled = false
        viewBinding?.chartProfitForecast?.setScaleEnabled(false)

        viewBinding?.chartProfitForecast?.xAxis?.position = XAxis.XAxisPosition.BOTTOM
        viewBinding?.chartProfitForecast?.xAxis?.axisMinimum = -0.5F
        viewBinding?.chartProfitForecast?.xAxis?.mAxisMaximum = 4.5F
        viewBinding?.chartProfitForecast?.xAxis?.setDrawGridLines(false)
        viewBinding?.chartProfitForecast?.xAxis?.setLabelCount(5, false)
        viewBinding?.chartProfitForecast?.xAxis?.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return if (value == 4F) {
                    "${value.toInt() + 2016}(年)"
                } else {
                    "${value.toInt() + 2016}"
                }
            }
        }

        viewBinding?.chartProfitForecast?.axisLeft?.setDrawAxisLine(false)
        viewBinding?.chartProfitForecast?.axisLeft?.axisMinimum = 0F
        viewBinding?.chartProfitForecast?.axisLeft?.gridColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartProfitForecast?.axisLeft?.axisLineColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartProfitForecast?.axisLeft?.textColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartProfitForecast?.axisRight?.isEnabled = false
        viewBinding?.chartProfitForecast?.axisLeft?.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "${value.toInt()}.00"
            }
        }

        viewBinding?.chartProfitForecast?.xAxis?.yOffset = 10F
        viewBinding?.chartProfitForecast?.xAxis?.textColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartProfitForecast?.legend?.horizontalAlignment =
            Legend.LegendHorizontalAlignment.CENTER
        viewBinding?.chartProfitForecast?.legend?.xEntrySpace = 100F
        viewBinding?.chartProfitForecast?.legend?.textColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartProfitForecast?.legend?.form = Legend.LegendForm.CIRCLE
        viewBinding?.chartProfitForecast?.legend?.yOffset = 5F
        viewBinding?.chartProfitForecast?.extraBottomOffset = 5F  //设置x轴底部和legend之间的间距

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

        viewBinding?.chartProfitForecast?.data = barData
        viewBinding?.chartProfitForecast?.invalidate()
    }

    private fun initCashFlow() {
        viewBinding?.chartCashFlow?.setBackgroundColor(Color.parseColor("#59886b"))
        viewBinding?.chartCashFlow?.setNoDataText(null)
        viewBinding?.chartCashFlow?.drawOrder = arrayOf(
            CombinedChart.DrawOrder.BAR,
            CombinedChart.DrawOrder.LINE
        )
        viewBinding?.chartCashFlow?.xAxis?.setDrawGridLines(false)
        viewBinding?.chartCashFlow?.axisLeft?.setDrawGridLines(false)
        viewBinding?.chartCashFlow?.animateX(400)
        viewBinding?.chartCashFlow?.description?.isEnabled = false
        viewBinding?.chartCashFlow?.setScaleEnabled(false)
        viewBinding?.chartCashFlow?.axisLeft?.gridColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartCashFlow?.axisLeft?.axisLineColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartCashFlow?.xAxis?.axisMinimum = -0.5F
        viewBinding?.chartCashFlow?.xAxis?.axisMaximum = 4.5F
        viewBinding?.chartCashFlow?.axisLeft?.axisMinimum = 0F
        viewBinding?.chartCashFlow?.axisLeft?.axisMaximum = 80F
        viewBinding?.chartCashFlow?.axisRight?.isEnabled = false
        viewBinding?.chartCashFlow?.xAxis?.setDrawGridLines(false)
        viewBinding?.chartCashFlow?.axisLeft?.setDrawLabels(false)
        viewBinding?.chartCashFlow?.xAxis?.position = XAxis.XAxisPosition.BOTTOM
        viewBinding?.chartCashFlow?.xAxis?.gridColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartCashFlow?.xAxis?.axisLineColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartCashFlow?.xAxis?.setLabelCount(5, false)
        viewBinding?.chartCashFlow?.xAxis?.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "${2016 + value.toInt()}年"
            }
        }
        viewBinding?.chartCashFlow?.xAxis?.yOffset = 10F
        viewBinding?.chartCashFlow?.xAxis?.textColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartCashFlow?.legend?.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        viewBinding?.chartCashFlow?.legend?.xEntrySpace = 100F
        viewBinding?.chartCashFlow?.legend?.textColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartCashFlow?.legend?.form = Legend.LegendForm.CIRCLE
        viewBinding?.chartCashFlow?.legend?.yOffset = 5F
        viewBinding?.chartCashFlow?.extraBottomOffset = 5F  //设置x轴底部和legend之间的间距

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
        viewBinding?.chartCashFlow?.data = combinedData
        viewBinding?.chartCashFlow?.invalidate()
    }

    private fun initValueLevel() {
        viewBinding?.chartValueLevel?.setBackgroundColor(Color.parseColor("#ec5858"))
        viewBinding?.chartValueLevel?.setNoDataText(null)
        viewBinding?.chartValueLevel?.setScaleEnabled(false)
        viewBinding?.chartValueLevel?.renderer?.paintRender?.strokeCap = Paint.Cap.ROUND
        viewBinding?.chartValueLevel?.animateX(500)
        viewBinding?.chartValueLevel?.axisLeft?.axisMinimum = 0F
        viewBinding?.chartValueLevel?.axisLeft?.setDrawAxisLine(true)
        viewBinding?.chartValueLevel?.axisLeft?.setDrawGridLines(false)
        viewBinding?.chartValueLevel?.axisLeft?.axisMaximum = 70F
        viewBinding?.chartValueLevel?.axisRight?.isEnabled = false
        viewBinding?.chartValueLevel?.axisLeft?.gridColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartValueLevel?.axisLeft?.axisLineColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartValueLevel?.axisLeft?.setDrawLabels(false)
        viewBinding?.chartValueLevel?.description?.isEnabled = false
        viewBinding?.chartValueLevel?.xAxis?.gridColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartValueLevel?.xAxis?.axisLineColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartValueLevel?.xAxis?.position = XAxis.XAxisPosition.BOTTOM
        viewBinding?.chartValueLevel?.xAxis?.setDrawLabels(true)
        viewBinding?.chartValueLevel?.xAxis?.setDrawGridLines(false)
        viewBinding?.chartValueLevel?.xAxis?.axisMinimum = -0.5F
        viewBinding?.chartValueLevel?.xAxis?.axisMaximum = 19.5F
        viewBinding?.chartValueLevel?.xAxis?.setLabelCount(5, false)
        viewBinding?.chartValueLevel?.xAxis?.textColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartValueLevel?.xAxis?.yOffset = 10F
        viewBinding?.chartValueLevel?.xAxis?.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "${(value + 2016).toInt()}年"
            }
        }
        viewBinding?.chartValueLevel?.legend?.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        viewBinding?.chartValueLevel?.legend?.xEntrySpace = 100F
        viewBinding?.chartValueLevel?.legend?.textColor = Color.parseColor("#fbf6f0")
        viewBinding?.chartValueLevel?.legend?.form = Legend.LegendForm.CIRCLE
        viewBinding?.chartValueLevel?.legend?.yOffset = 5F
        viewBinding?.chartValueLevel?.extraBottomOffset = 5F  //设置x轴底部和legend之间的间距

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
        viewBinding?.chartValueLevel?.data = lineData
        viewBinding?.chartValueLevel?.invalidate()
    }
}