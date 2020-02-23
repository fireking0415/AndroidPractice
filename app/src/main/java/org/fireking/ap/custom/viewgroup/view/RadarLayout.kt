package org.fireking.ap.custom.viewgroup.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.LinearInterpolator
import org.fireking.ap.R

class RadarLayout(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    ViewGroup(context, attrs, defStyleAttr) {

    /**
     * 雷达半径
     */
    private var mRadarRadius: Int = 0

    /**
     * 圆形圈递增半径-元半径
     */
    private var mRadarCircleWith: Int = 0

    /**
     * 圆环画笔
     */
    private lateinit var mCirclePaint: Paint

    /**
     * 扫描画笔
     */
    private lateinit var mSweepPaint: Paint

    /**
     * 视图宽度
     */
    private var mWidth: Int = 0

    /**
     * 视图高度
     */
    private var mHeight: Int = 0

    /**
     * 渲染扫描颜色
     */
    private var mSweepColor: Int = 0

    /**
     * 绘制圆环个数
     */
    private val sCircleSize = 5

    /**
     * 旋转角度
     */
    private var angle = 0f

    private var valueAnimator: ValueAnimator? = null

    private lateinit var mUserAvatarBitmap: Bitmap

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        setWillNotDraw(false)

        mCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
        mCirclePaint.style = Paint.Style.STROKE
        mCirclePaint.isAntiAlias = true//抗锯齿
        mCirclePaint.color = Color.parseColor("#454545")
        mCirclePaint.strokeWidth = 0.5f//画笔宽度

        mSweepPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
        mSweepPaint.style = Paint.Style.FILL

        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val metrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(metrics)

        mSweepColor = Color.parseColor("#454545")

        //绘制圆环递增半径
        mRadarCircleWith = metrics.widthPixels / 2 / 4

        //通过屏幕半径换算出需要实际绘制的图形的半径
        mRadarRadius = metrics.widthPixels / 2 + mRadarCircleWith

        val srcBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_user_info)
        mUserAvatarBitmap = Bitmap.createScaledBitmap(srcBitmap, mRadarCircleWith * 2, mRadarCircleWith * 2, false)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startSweepAnimation()
    }

    private fun startSweepAnimation() {
        if (valueAnimator == null) {
            valueAnimator = ValueAnimator.ofFloat(0F, 359.9f)
            valueAnimator?.duration = 4800
            valueAnimator?.interpolator = LinearInterpolator()
            valueAnimator?.repeatCount = ValueAnimator.INFINITE
            valueAnimator?.addUpdateListener {
                angle = valueAnimator?.animatedValue as Float
                invalidate()
            }
        }
        valueAnimator?.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopSweepAnimation()
    }

    private fun stopSweepAnimation() {
        valueAnimator?.cancel()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //绘制圆圈
        drawCircle(canvas)

        //绘制扫描
        drawSweep(canvas)

        //绘制用户头像
        drawUserAvatar(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
    }

    private fun drawUserAvatar(canvas: Canvas) {
        canvas.drawBitmap(mUserAvatarBitmap, mWidth / 2f - mRadarCircleWith, mHeight / 2f - mRadarCircleWith, null)
    }

    private fun drawSweep(canvas: Canvas) {
        canvas.save()
        canvas.rotate(-90 + angle, mWidth / 2f, mHeight / 2f)
        val sweepGradient = SweepGradient(
            mWidth / 2f, mHeight / 2f, intArrayOf(
                changeAlpha(mSweepColor, 0),
                changeAlpha(mSweepColor, 95),
                changeAlpha(mSweepColor, 128),

                changeAlpha(mSweepColor, 95),
                changeAlpha(mSweepColor, 128),
                changeAlpha(mSweepColor, 168),

                changeAlpha(mSweepColor, 95),
                changeAlpha(mSweepColor, 168),
                changeAlpha(mSweepColor, 200),
                changeAlpha(mSweepColor, 255),
                changeAlpha(Color.parseColor("#18609c"), 255)
            ), floatArrayOf(0f, 0.2f, 0.4f, 0.5f, 0.6f, 0.7f, 0.78f, 0.86f, 0.9f, 0.999f, 1f)
        )
        mSweepPaint.shader = sweepGradient
        canvas.drawCircle(mWidth / 2f, mHeight / 2f, (mRadarCircleWith * sCircleSize).toFloat(), mSweepPaint)
        canvas.restore()
    }

    private fun drawCircle(canvas: Canvas) {
        for (i in 1..sCircleSize) {
            canvas.drawCircle(mWidth / 2f, mHeight / 2f, (mRadarCircleWith * i).toFloat(), mCirclePaint)
        }
    }

    private fun changeAlpha(color: Int, alpha: Int): Int {
        val red = Color.red(color)
        val green = Color.red(color)
        val blue = Color.blue(color)
        return Color.argb(alpha, red, green, blue)
    }

    /**
     * 添加用户头像
     *
     * @param url
     */
    fun setUserAvatar(result: Bitmap) {
        val resultBitmap = getOvalBitmap(result)
        mUserAvatarBitmap = Bitmap.createScaledBitmap(
            resultBitmap,
            mRadarCircleWith * 2, mRadarCircleWith * 2, false
        )
        invalidate()
    }

    private fun getOvalBitmap(bitmap: Bitmap): Bitmap {

        val output = Bitmap.createBitmap(
            bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(output)

        val color = -0xbdbdbe
        val paint = Paint()
        val rect = Rect(0, 0, bitmap.width, bitmap.height)

        val rectF = RectF(rect)

        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = color

        canvas.drawOval(rectF, paint)

        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)
        return output
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //这里根据业务需求直接绘制出来一个超出屏幕宽度的正方矩形
        setMeasuredDimension(
            MeasureSpec.makeMeasureSpec(mRadarRadius * 2, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(mRadarRadius * 2, MeasureSpec.EXACTLY)
        )

        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
    }
}