package org.fireking.ap.custom.basic.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class TaiChiDiagramView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mTaiChiPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mTaiChiPath = Path()

    init {
        mTaiChiPaint.style = Paint.Style.FILL_AND_STROKE
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
        canvas?.save()
        canvas?.rotate(90F, (width / 2).toFloat(), (height / 2).toFloat())
        val rect = RectF(0F, 0F, width.toFloat(), height.toFloat())
        mTaiChiPaint.color = Color.parseColor("#f1f1f1")
        canvas?.drawArc(rect, 0F, 180F, true, mTaiChiPaint)
        mTaiChiPaint.color = Color.parseColor("#23120b")
        canvas?.drawArc(rect, 180F, 180F, true, mTaiChiPaint)
        canvas?.restore()
        mTaiChiPaint.color = Color.parseColor("#f1f1f1")
        canvas?.drawCircle(
            (width / 2).toFloat(),
            (height / 4).toFloat(),
            (width / 4).toFloat(),
            mTaiChiPaint
        )
        mTaiChiPaint.color = Color.parseColor("#23120b")
        canvas?.drawCircle(
            (width / 2).toFloat(),
            (height / 4 * 3).toFloat(),
            (width / 4).toFloat(),
            mTaiChiPaint
        )
        canvas?.drawCircle(
            (width / 2).toFloat(),
            (height / 4).toFloat(),
            (width / 12).toFloat(),
            mTaiChiPaint
        )
        mTaiChiPaint.color = Color.parseColor("#f1f1f1")
        canvas?.drawCircle(
            (width / 2).toFloat(),
            (height / 4 * 3).toFloat(),
            (width / 12).toFloat(),
            mTaiChiPaint
        )
    }
}