package org.fireking.ap.custom.basic.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import org.fireking.ap.R
import kotlin.properties.Delegates

class SimpleBarChart : View {

    private var mLabelColor by Delegates.notNull<Int>()
    private var mGridLineColor by Delegates.notNull<Int>()
    private var mOneBarColor by Delegates.notNull<Int>()
    private var mTwoBarColor by Delegates.notNull<Int>()
    private var mGridLineWidth by Delegates.notNull<Float>()
    private var mLabelFontSize by Delegates.notNull<Float>()

    private var mContentWidth: Float = 0F
    private var mContentHeight: Float = 0F

    private var mXAxisLabelHeight: Float = 0F
    private var mYAxisLabelWidth: Float = 0F

    private var mLabelPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mGridLinePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mBarOnePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mBarTwoPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var mOneBarEntityList = ArrayList<Float>()
    private var mTwoBarEntityList = ArrayList<Float>()
    private var mXAxisLabelList = ArrayList<String>()

    private var mYAxisMaxValue: Float = 0F
    private var mAxisOffsetWithContent: Float = 0F

    companion object {
        private const val DEFAULT_GRID_LINE_WIDTH = 0.5F
        private const val DEFAULT_FONT_SIZE = 10F
        private const val DEFAULT_COLOR = Color.BLACK
        private const val DEFAULT_Y_AXIS_GRID_LINE_COUNT = 4
    }

    constructor(context: Context) : super(context) {
        initView(context, null)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        initDefaultValue()
        initView(context, attributeSet)
        initPaint()
    }

    private fun initDefaultValue() {
        mAxisOffsetWithContent = dip2px(context, 4F)
    }

    private fun initPaint() {
        mLabelPaint.textSize = mLabelFontSize
        mLabelPaint.color = mLabelColor
        mLabelPaint.style = Paint.Style.FILL_AND_STROKE

        mGridLinePaint.style = Paint.Style.FILL
        mGridLinePaint.strokeWidth = mGridLineWidth
        mGridLinePaint.color = mGridLineColor
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (w == oldw && h == oldh) {
            return
        }
        calculationXAxisLabelHeight(w, h)
        calculationYAxisLabelWidth(w, h)
        calculationContentWidth(w, h)
        calculationContentHeight(w, h)
    }

    private fun calculationYAxisMaxLengthValue(): String {
        val maxOneBarValue = mOneBarEntityList.maxBy { it.toString().length }
        val maxTwoBarValue = mTwoBarEntityList.maxBy { it.toString().length }
        return if (maxOneBarValue?.toString()?.length ?: 0 <= maxTwoBarValue?.toString()?.length ?: 0) {
            maxTwoBarValue?.toString() ?: ""
        } else {
            maxOneBarValue?.toString() ?: ""
        }
    }

    private fun calculationYAxisMaxValue() {
        val maxOneBarValue = mOneBarEntityList.maxBy { it } ?: 0F
        val maxTwoBarValue = mTwoBarEntityList.maxBy { it } ?: 0F
        mYAxisMaxValue = maxOneBarValue.coerceAtLeast(maxTwoBarValue)
    }

    private fun calculationContentHeight(w: Int, h: Int) {
        mContentHeight = h - mXAxisLabelHeight
    }

    private fun calculationContentWidth(w: Int, h: Int) {
        mContentWidth = w - mYAxisLabelWidth
    }

    private fun calculationYAxisLabelWidth(w: Int, h: Int) {
        mYAxisLabelWidth = mLabelPaint.measureText(calculationYAxisMaxLengthValue())
    }

    private fun calculationXAxisLabelHeight(w: Int, h: Int) {
        val rect = Rect()
        val textBound = if (mXAxisLabelList.size > 0) {
            mXAxisLabelList[0]
        } else {
            "ABC"
        }
        mLabelPaint.getTextBounds(textBound, 0, textBound.length, rect)
        mXAxisLabelHeight = rect.height().toFloat()
    }

    private fun initView(context: Context, attributeSet: AttributeSet?) {
        setupAttributeSet(context, attributeSet)
    }

    private fun setupAttributeSet(context: Context, attributeSet: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.SimpleBarChart)
        mLabelColor = typedArray.getColor(R.styleable.SimpleBarChart_labelColor, DEFAULT_COLOR)
        mGridLineColor =
            typedArray.getColor(R.styleable.SimpleBarChart_gridLineColor, DEFAULT_COLOR)
        mOneBarColor = typedArray.getColor(R.styleable.SimpleBarChart_barOneColor, DEFAULT_COLOR)
        mTwoBarColor = typedArray.getColor(R.styleable.SimpleBarChart_barTwoColor, DEFAULT_COLOR)
        val gridLineWidth =
            typedArray.getFloat(R.styleable.SimpleBarChart_gridLineWidth, DEFAULT_GRID_LINE_WIDTH)
        mGridLineWidth = dip2px(context, gridLineWidth)
        val labelFontSize =
            typedArray.getFloat(R.styleable.SimpleBarChart_labelFontSize, DEFAULT_FONT_SIZE)
        mLabelFontSize = dip2px(context, labelFontSize)
        typedArray.recycle()
    }

    private fun dip2px(context: Context, value: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value,
            context.resources.displayMetrics
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        renderAxisBasic(canvas)
        renderGridLine(canvas)
        renderLabel(canvas)
        renderBar(canvas)
    }

    private fun renderAxisBasic(canvas: Canvas?) {
        canvas?.drawColor(Color.parseColor("#d35d6e"))
        canvas?.translate(0F, measuredHeight.toFloat())
    }

    private fun renderBar(canvas: Canvas?) {
    }

    private fun renderLabel(canvas: Canvas?) {
        renderXAxisLabel(canvas)
        renderYAxisLabel(canvas)
    }

    private fun renderYAxisLabel(canvas: Canvas?) {

    }

    private fun renderXAxisLabel(canvas: Canvas?) {

    }

    private fun renderGridLine(canvas: Canvas?) {
        renderXAxisLine(canvas)
        renderYAxisLine(canvas)
        renderXGridLine(canvas)
        renderYGridLine(canvas)
    }

    private fun renderYAxisLine(canvas: Canvas?) {
        canvas?.drawLine(
            mYAxisLabelWidth,
            -mXAxisLabelHeight,
            mYAxisLabelWidth,
            -(mXAxisLabelHeight + mContentHeight),
            mGridLinePaint
        )
    }

    private fun renderXAxisLine(canvas: Canvas?) {
        Log.e("info", "$mXAxisLabelHeight-$mYAxisLabelWidth-$mContentWidth-$mContentHeight")
        canvas?.drawLine(
            mYAxisLabelWidth,
            -(mXAxisLabelHeight + mAxisOffsetWithContent),
            mYAxisLabelWidth + mContentWidth,
            -(mXAxisLabelHeight + mAxisOffsetWithContent ),
            mGridLinePaint
        )
        canvas?.drawText("2018", 120F, 0F, mLabelPaint)
    }

    private fun renderYGridLine(canvas: Canvas?) {

    }

    private fun renderXGridLine(canvas: Canvas?) {

    }

    fun setData(
        oneBarList: ArrayList<Float>,
        twoBarList: ArrayList<Float>,
        xAxisLabel: ArrayList<String>
    ) {
        mOneBarEntityList.clear()
        mOneBarEntityList.addAll(oneBarList)
        mTwoBarEntityList.clear()
        mTwoBarEntityList.addAll(twoBarList)
        mXAxisLabelList.clear()
        mXAxisLabelList.addAll(xAxisLabel)
        requestLayout()
        invalidate()
    }

}