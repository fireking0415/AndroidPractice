package org.fireking.ap.custom.mpchart.radarchart

import android.graphics.Canvas
import android.graphics.Path
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.renderer.XAxisRendererRadarChart
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.utils.Utils
import com.github.mikephil.charting.utils.ViewPortHandler

class SimpleXAxisRendererRadarChart(
    viewPortHandler: ViewPortHandler?,
    xAxis: XAxis?,
    private val radarChart: RadarChart
) : XAxisRendererRadarChart(viewPortHandler, xAxis, radarChart) {

    override fun drawGridLine(c: Canvas?, x: Float, y: Float, gridLinePath: Path?) {
    }

    override fun renderAxisLabels(c: Canvas?) {
        if (!mXAxis.isEnabled || !mXAxis.isDrawLabelsEnabled) {
            return
        }
        val labelRotationAngleDegrees = mXAxis.labelRotationAngle
        val drawLabelAnchor = MPPointF.getInstance(0.5f, 0.25f)
        mAxisLabelPaint.typeface = mXAxis.typeface
        mAxisLabelPaint.textSize = mXAxis.textSize
        mAxisLabelPaint.color = mXAxis.textColor
        val sliceangle = radarChart.sliceAngle

        val factor = radarChart.factor
        val center = radarChart.centerOffsets
        val pOut = MPPointF.getInstance(0f, 0f)
        for (i in 0 until radarChart.data.maxEntryCountSet.entryCount) {
            val label = mXAxis.valueFormatter.getAxisLabel(i.toFloat(), mXAxis)
            val angle = (sliceangle * i + radarChart.rotationAngle) % 360f
            Utils.getPosition(
                center,
                radarChart.yRange * factor + mXAxis.mLabelRotatedWidth / 2f,
                angle,
                pOut
            )
            drawLabel(
                c, label, pOut.x, pOut.y - mXAxis.mLabelRotatedHeight / 2f,
                drawLabelAnchor, labelRotationAngleDegrees
            )
        }
        MPPointF.recycleInstance(center)
        MPPointF.recycleInstance(pOut)
        MPPointF.recycleInstance(drawLabelAnchor)
    }

}