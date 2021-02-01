package org.fireking.basic.viewgroup.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View

class WaveBackgroundView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var borderPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.CURSOR_AFTER)
    private var circleRadius: Float = 0F
    private var borderWidth: Float
    private var marginBorder: Float

    private var wavePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.CURSOR_AFTER)
    private var waveWidth: Float
    private var waveRadius: Float = 0F
    private var animatorRadius: Float = 0F

    private var heartAnimator: ValueAnimator

    init {
        borderPaint.color = Color.parseColor("#F14400")
        borderPaint.style = Paint.Style.STROKE
        borderWidth =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2F, resources.displayMetrics)
        borderPaint.strokeWidth = borderWidth
        marginBorder =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10F, resources.displayMetrics)

        wavePaint.color = Color.parseColor("#F14400")
        wavePaint.style = Paint.Style.STROKE
        waveWidth =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1F, resources.displayMetrics)
        wavePaint.strokeWidth = waveWidth

        heartAnimator = ValueAnimator.ofFloat(0F, 1F).apply {
            addUpdateListener {
                val animatorValue = it.animatedValue as Float
                wavePaint.alpha = (255 * (1 - animatorValue)).toInt()
                animatorRadius =
                    waveRadius + animatorValue * (marginBorder - borderWidth - waveWidth)
                invalidate()
            }
            repeatCount = ValueAnimator.INFINITE
            duration = 1500L
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        circleRadius = measuredWidth / 2F - (marginBorder - borderWidth)
        waveRadius = circleRadius + waveWidth
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //将坐标原点移动到中心
        canvas.translate(width / 2F, height / 2F)
        //绘制圆环背景
        canvas.drawCircle(0F, 0F, circleRadius, borderPaint)
        //绘制扩展水波纹效果
        canvas.drawCircle(0F, 0F, animatorRadius, wavePaint)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (heartAnimator.isRunning) {
            heartAnimator.end()
        }
        heartAnimator.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (heartAnimator.isRunning) {
            heartAnimator.end()
        }
    }
}