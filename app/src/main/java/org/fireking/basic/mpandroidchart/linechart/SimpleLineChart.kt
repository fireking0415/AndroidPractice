package org.fireking.basic.mpandroidchart.linechart

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import com.github.mikephil.charting.charts.LineChart

class SimpleLineChart(context: Context?, attrs: AttributeSet?) : LineChart(context, attrs) {

    companion object {
        const val DEFAULT_EMPTY_LINES = 5
    }

    val hasData = mData != null

    override fun init() {
        super.init()
        mXAxisRenderer = SimpleXAxisRenderer(this, viewPortHandler, mXAxis, mLeftAxisTransformer)
        setNoDataText(null)
    }

    override fun onDraw(canvas: Canvas?) {
        if (mData == null) {
            drawEmptyView(canvas)
            return
        }
        super.onDraw(canvas)
    }

    private fun drawEmptyView(canvas: Canvas?) {
        mXAxisRenderer.renderGridLines(canvas)
        mXAxisRenderer.renderAxisLine(canvas)
        mXAxisRenderer.renderAxisLabels(canvas)
    }
}
