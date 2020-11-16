package org.fireking.ap.custom.mpchart.basic

import android.content.Context
import android.graphics.*
import android.util.TypedValue
import com.github.mikephil.charting.animation.ChartAnimator
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider
import com.github.mikephil.charting.renderer.LineChartRenderer
import com.github.mikephil.charting.utils.ViewPortHandler

class SimpleLineChartRenderer(
    context: Context,
    chart: LineDataProvider?,
    animator: ChartAnimator?,
    viewPortHandler: ViewPortHandler?
) : LineChartRenderer(chart, animator, viewPortHandler) {

    private val mRectPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mLinePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        mTextPaint.textSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            8F,
            context.resources.displayMetrics
        )
        mTextPaint.color = Color.WHITE

        mRectPaint.color = Color.parseColor("#80F14400")

        mLinePaint.strokeWidth = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            0.8F,
            context.resources.displayMetrics
        )
        mLinePaint.color = Color.parseColor("#80F14400")
    }

    override fun drawValues(canvas: Canvas?) {
        val dataSetByIndex = mChart.lineData.getDataSetByIndex(0) as LineDataSet
        val maxYEntry = dataSetByIndex.values[3]
        val transformer = mChart.getTransformer(dataSetByIndex.axisDependency)
        val mpPointD = transformer.getPixelForValues(maxYEntry.x, maxYEntry.y)

        canvas?.save()
        canvas?.translate(mpPointD.x.toFloat() - 80 / 2, mpPointD.y.toFloat() - 100)
        //绘制圆角矩形
        canvas?.drawRoundRect(
            RectF(
                0F,
                0F,
                80F,
                36F
            ), 5F, 5F, mRectPaint
        )
        //绘制文字
        val label = "看多"
        val labelWidth = mTextPaint.measureText(label)
        val labelRect = Rect()
        mTextPaint.getTextBounds(label, 0, label.length, labelRect)
        val fontMetrics = mTextPaint.fontMetrics
        canvas?.drawText(
            label,
            80F / 2 - labelWidth / 2,
            (fontMetrics.descent - fontMetrics.ascent),
            mTextPaint
        )
        canvas?.restore()
        canvas?.save()
        canvas?.translate(mpPointD.x.toFloat(), mpPointD.y.toFloat() - (100 - 36))
        //绘制连接线
        canvas?.drawLine(
            0F,
            0F,
            0F,
            100F - 36F,
            mLinePaint
        )
        canvas?.restore()
    }
}