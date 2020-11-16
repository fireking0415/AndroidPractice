package org.fireking.ap.custom.mpchart.basic

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.android.synthetic.main.activity_m_p_chart_basic_v1.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor
import kotlin.random.Random

class LineChartActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<LineChartActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m_p_chart_basic_v1)
        drawLineChartView1()
        drawLineChartView2()
        drawLineChartView3()
    }

    private fun drawLineChartView3() {

    }

    private fun drawLineChartView2() {
        //设置lineChart基本属性
        lineChartView2.description.text = ""
        lineChartView2.description.textColor = Color.RED
        lineChartView2.description.textSize = 16F
        lineChartView2.setNoDataText("无可用数据")  //没数据的时候展示
        lineChartView2.setDrawBorders(false)// 是否绘制边框
        lineChartView2.animateX(500)  //x轴动画
        lineChartView2.setTouchEnabled(true)  //设置支持触摸
        lineChartView2.setScaleEnabled(true)  //是否支持缩放，默认true
        lineChartView2.isDragEnabled = true //是否支持拖拽
//        lineChartView2.isScaleXEnabled = true //是否支持x轴缩放
//        lineChartView2.isScaleYEnabled = true //是否支持y轴缩放
//        lineChartView2.setPinchZoom(true)  //是否支持x、y轴同时缩放，默认为false
        lineChartView2.isDoubleTapToZoomEnabled = true //是否支持双击屏幕放大，默认true
        lineChartView2.isHighlightPerDragEnabled = true  //是否拖拽高亮线（数据点和坐标的提示线），默认true
        lineChartView2.isDragDecelerationEnabled = true //拖拽滚动时，手放开是否会持续滚动，默认true，false是拖动到那算那
        lineChartView2.dragDecelerationFrictionCoef = 0.99F //和上面的属性相配合，配置持续滚动的速度快慢，区间【0-1】0表示立即停止

        //绘制x轴
        val xAxis = lineChartView2.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM //设置x轴位置
//        xAxis.axisMaximum = 1F //设置x轴最小值
        xAxis.textSize = 14F
        xAxis.textColor = Color.RED
//        xAxis.isEnabled = true //是否显示x轴是否禁用
//        xAxis.setDrawLabels(true) //设置x轴标签展示
//        xAxis.setDrawGridLines(true) //设置设置x轴上每个对应的点的竖线
//        xAxis.enableAxisLineDashedLine(2F, 2F, 2F) //竖线 -虚线样式
//        xAxis.labelRotationAngle = 30F //设置x轴标签的旋转角度
//
//        //绘制y轴
//        val yAxisLeft = lineChartView2.axisLeft
//        yAxisLeft.textSize = 14F
//        yAxisLeft.axisMaximum = 0F
        val yAxisRight = lineChartView2.axisRight
        yAxisRight.isEnabled = false

        val lineDataEntities = ArrayList<Entry>()
        val random = Random(10)
        for (index in 1 until 20) {
            lineDataEntities.add(Entry(index.toFloat(), random.nextFloat()))
        }

        val lineDataSet = LineDataSet(lineDataEntities, "股票走势曲线")
        lineDataSet.highLightColor = Color.RED  //设置高亮线的颜色
        lineDataSet.color = Color.BLACK  //折线颜色
        lineDataSet.setCircleColor(Color.BLUE)  //设置交点的圆圈颜色
        lineDataSet.setDrawCircles(false)  //是否绘制交点
        lineDataSet.setDrawValues(false)  //是否显示交叉点的数值
        lineDataSet.valueTextColor = Color.CYAN  //设置交叉点上的值的颜色
        lineDataSet.valueTextSize = 14F //设置交叉点上值的字体大小
        lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER //设置平滑曲线
        lineDataSet.fillColor = Color.parseColor("#F14400")
        lineDataSet.setDrawFilled(true)

        val lineData = LineData(lineDataSet)
        lineChartView2.data = lineData
        lineChartView2.invalidate()
    }

    private fun drawLineChartView1() {
        val lineEntities = ArrayList<Entry>()
        val lineTwoEntities = ArrayList<Entry>()
        for (index in 0 until 12) {
            lineEntities.add(Entry(index.toFloat(), Random.nextInt(300).toFloat()))
            lineTwoEntities.add(Entry(index.toFloat(), Random.nextInt(300).toFloat()))
        }
        val lineDataSet = LineDataSet(lineEntities, "One")
        lineDataSet.setCircleColor(Color.parseColor("#67BCFF"))  //设置链接点的颜色
        lineDataSet.color = Color.parseColor("#67BCFF")  //设置线的颜色
        lineDataSet.setDrawCircleHole(false)  //设置绘制点是空心还是实心，默认true，实心为false
        lineDataSet.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
        lineDataSet.setDrawCircles(false)
        lineDataSet.setDrawValues(false)
        lineDataSet.setDrawFilled(true)

        val lineTwoDataSet = LineDataSet(lineTwoEntities, "Two")
        lineTwoDataSet.isHighlightEnabled = true  //设置那一条线在最上面
        lineTwoDataSet.setCircleColor(Color.parseColor("#F14400"))
        lineTwoDataSet.color = Color.parseColor("#F14400")
        lineTwoDataSet.setDrawCircles(false)  //是否绘制链接点，默认为true
        lineTwoDataSet.setDrawValues(false) //是否绘制链接点的文字，默认为true
        lineTwoDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER

        //控制坐标轴属性
        val xAxis = lineChartView.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM //设置x轴的位置
        xAxis.setLabelCount(lineEntities.size, true)
        xAxis.granularity = 1F //设置x轴坐标间的最小间距
        xAxis.axisMaximum = (lineEntities.size - 1).toFloat()
        xAxis.axisMinimum = 0F
        xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "${lineEntities[value.toInt()].x}天"
            }
        }
        xAxis.isAvoidFirstLastClippingEnabled

        val yAxisRight = lineChartView.axisRight
        yAxisRight.isEnabled = false
        val yAxisLeft = lineChartView.axisLeft
        yAxisLeft.axisMinimum = 0F
        yAxisLeft.setDrawZeroLine(true)
        yAxisLeft.mAxisMaximum = 300F

        val lineData = LineData()
        lineData.addDataSet(lineDataSet)
        lineData.addDataSet(lineTwoDataSet)
        lineData.setDrawValues(false)

        lineChartView.description.isEnabled = false
        lineChartView.setDrawGridBackground(true)
        lineChartView.setDrawBorders(true)

        lineChartView.marker = SimpleMarkerView(this)
        lineChartView.renderer = SimpleLineChartRenderer(
            this,
            lineChartView,
            lineChartView.animator,
            lineChartView.viewPortHandler
        )
        lineChartView.animateX(1500)
        lineChartView.isScaleXEnabled = true
        lineChartView.isScaleYEnabled = false

        lineChartView.data = lineData
        lineChartView.invalidate()
    }
}