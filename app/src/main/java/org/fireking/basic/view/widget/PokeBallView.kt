package org.fireking.basic.view.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class PokeBallView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mPokeBallPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        mPokeBallPaint.style = Paint.Style.FILL_AND_STROKE
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
        val rect = RectF(0F, 0F, width.toFloat(), height.toFloat())
        mPokeBallPaint.color = Color.parseColor("#f1f1f1")
        canvas?.drawArc(rect, 0F, 180F, true, mPokeBallPaint)
        mPokeBallPaint.color = Color.parseColor("#ff4646")
        canvas?.drawArc(rect, 180F, 180F, true, mPokeBallPaint)
        mPokeBallPaint.color = Color.parseColor("#23120b")
        canvas?.drawCircle(
            (width / 2).toFloat(), (height / 2).toFloat(),
            (width / 6).toFloat(), mPokeBallPaint
        )
        mPokeBallPaint.color = Color.parseColor("#f1f1f1")
        canvas?.drawCircle(
            (width / 2).toFloat(), (height / 2).toFloat(),
            (width / 8).toFloat(), mPokeBallPaint
        )
        canvas?.drawCircle(
            (width / 2).toFloat(), (height / 2).toFloat(),
            (width / 8).toFloat(), mPokeBallPaint
        )
    }
}