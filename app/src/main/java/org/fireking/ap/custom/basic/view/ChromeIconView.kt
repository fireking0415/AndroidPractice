package org.fireking.ap.custom.basic.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class ChromeIconView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mGreenPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mGreenPath = Path()

    init {
        mGreenPaint.style = Paint.Style.FILL_AND_STROKE
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        super.onMeasure(
            heightMeasureSpec,
            MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY)
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(mGreenPath, mGreenPaint)
    }
}