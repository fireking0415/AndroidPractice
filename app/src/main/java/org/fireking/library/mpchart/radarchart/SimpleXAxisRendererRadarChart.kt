package org.fireking.library.mpchart.radarchart

import android.graphics.Canvas
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.renderer.XAxisRendererRadarChart
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.utils.Utils
import com.github.mikephil.charting.utils.ViewPortHandler

class SimpleXAxisRendererRadarChart(
    viewPortHandler: ViewPortHandler?,
    xAxis: XAxis?,
    private val radarChart: RadarChart
) : XAxisRendererRadarChart(viewPortHandler, xAxis, radarChart) {

    override fun renderAxisLabels(c: Canvas?) {
        if (!mXAxis.isEnabled || !mXAxis.isDrawLabelsEnabled) {
            return
        }
        val labelRotationAngleDegrees = mXAxis.labelRotationAngle
        val drawLabelAnchor = MPPointF.getInstance(0.5f, 0.25f)
        mAxisLabelPaint.typeface = mXAxis.typeface
        mAxisLabelPaint.textSize = mXAxis.textSize
        mAxisLabelPaint.color = mXAxis.textColor
        val sliceAngle = radarChart.sliceAngle

        val factor = radarChart.factor
        val center = radarChart.centerOffsets
        val pOut = MPPointF.getInstance(0f, 0f)
        val radarDataSet = radarChart.data.dataSets[0] as RadarDataSet
        for (i in 0 until radarChart.data.maxEntryCountSet.entryCount) {
            val label = mXAxis.valueFormatter.getAxisLabel(i.toFloat(), mXAxis)
            val angle = (sliceAngle * i + radarChart.rotationAngle) % 360f
            Utils.getPosition(
                center,
                radarChart.yRange * factor + mXAxis.mLabelRotatedWidth / 2f,
                angle,
                pOut
            )
            drawRadarLabel(
                radarDataSet.values?.get(i),
                c, label, pOut.x, pOut.y - mXAxis.mLabelRotatedHeight / 2f,
                drawLabelAnchor, labelRotationAngleDegrees
            )
        }
        MPPointF.recycleInstance(center)
        MPPointF.recycleInstance(pOut)
        MPPointF.recycleInstance(drawLabelAnchor)
    }

    private fun drawRadarLabel(
        c1: RadarEntry?,
        c: Canvas?,
        formattedLabel: String?,
        x: Float,
        y: Float,
        anchor: MPPointF?,
        angleDegrees: Float
    ) {
        super.drawLabel(c, formattedLabel, x, y, anchor, angleDegrees)

        var label = "- -"
        if (c1?.value ?: 0 != 0F) {
            label = c1?.value?.toString() ?: "- -"
        }
        Utils.drawXAxisValue(
            c,
            label,
            x + 20,
            y + 30,
            mAxisLabelPaint,
            anchor,
            angleDegrees
        )
    }
}