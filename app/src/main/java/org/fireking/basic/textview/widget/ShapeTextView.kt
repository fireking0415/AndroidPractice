package org.fireking.basic.textview.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import org.fireking.ap.R

class ShapeTextView(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {

    private var mSolidColor = DEFAULT_SOLID_COLOR
    private var mUnusableSolidColor = DEFAULT_SOLID_COLOR
    private var mBorderColor = DEFAULT_SOLID_COLOR
    private var mUnusableTextColor: Int = DEFAULT_SOLID_COLOR
    private var mUnusableBorderColor = DEFAULT_SOLID_COLOR
    private var mGradientEnable = false
    private var mBorderWidth: Int = 3
    private var mDashWidth: Float = 0F
    private var mDashGapWidth: Float = 0F
    private lateinit var mGradientDrawable: GradientDrawable
    private var mCornerRadiiArrayList = FloatArray(8)
    private var mGradientColors = IntArray(2)
    private var mUnusableGradientColors = IntArray(2)
    private var mAngle: Int = 0

    companion object {
        const val TOP_BOTTOM = 1
        const val TR_BL = 2
        const val RIGHT_LEFT = 3
        const val BR_TL = 4
        const val BOTTOM_TOP = 5
        const val BL_TR = 6
        const val LEFT_RIGHT = 7
        const val TL_BR = 8
        private const val DEFAULT_SOLID_COLOR = Color.WHITE
    }

    init {
        obtainAttr(attrs)
    }

    private fun setDefaultCornerRadii(
        topLeft: Float,
        topRight: Float,
        bottomRight: Float,
        bottomLeft: Float
    ) {
        mCornerRadiiArrayList[0] = topLeft
        mCornerRadiiArrayList[1] = topLeft
        mCornerRadiiArrayList[2] = topRight
        mCornerRadiiArrayList[3] = topRight
        mCornerRadiiArrayList[4] = bottomRight
        mCornerRadiiArrayList[5] = bottomRight
        mCornerRadiiArrayList[6] = bottomLeft
        mCornerRadiiArrayList[7] = bottomLeft
    }

    private fun obtainAttr(attrs: AttributeSet?) {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.ShapeTextView)
        mBorderWidth = attr.getDimension(
            R.styleable.ShapeTextView_stv_width,
            mBorderWidth.toFloat()
        ).toInt()
        mSolidColor = attr.getColor(R.styleable.ShapeTextView_stv_solid, DEFAULT_SOLID_COLOR)
        mUnusableSolidColor =
            attr.getColor(R.styleable.ShapeTextView_stv_unusable_solid, DEFAULT_SOLID_COLOR)
        mBorderColor =
            attr.getColor(R.styleable.ShapeTextView_stv_border_color, DEFAULT_SOLID_COLOR)
        mUnusableBorderColor =
            attr.getColor(R.styleable.ShapeTextView_stv_unusable_border_color, DEFAULT_SOLID_COLOR)
        val commonCornerSize = attr.getDimension(R.styleable.ShapeTextView_stv_corners, 0F)
        setDefaultCornerRadii(
            commonCornerSize,
            commonCornerSize,
            commonCornerSize,
            commonCornerSize
        )
        var topLeftCornerSize = commonCornerSize
        if (attr.hasValue(R.styleable.ShapeTextView_stv_topLeft_corners)) {
            topLeftCornerSize = attr.getDimension(R.styleable.ShapeTextView_stv_topLeft_corners, 0F)
        }
        var topRightCornerSize = commonCornerSize
        if (attr.hasValue(R.styleable.ShapeTextView_stv_topRight_corners)) {
            topRightCornerSize =
                attr.getDimension(R.styleable.ShapeTextView_stv_topRight_corners, 0F)
        }
        var bottomLeftCornerSize = commonCornerSize
        if (attr.hasValue(R.styleable.ShapeTextView_stv_bottomLeft_corners)) {
            bottomLeftCornerSize =
                attr.getDimension(R.styleable.ShapeTextView_stv_bottomLeft_corners, 0F)
        }
        var bottomRightCornerSize = commonCornerSize
        if (attr.hasValue(R.styleable.ShapeTextView_stv_bottomRight_corners)) {
            bottomRightCornerSize =
                attr.getDimension(R.styleable.ShapeTextView_stv_bottomRight_corners, 0F)
        }
        setDefaultCornerRadii(
            topLeftCornerSize,
            topRightCornerSize,
            bottomRightCornerSize,
            bottomLeftCornerSize
        )
        mGradientEnable = attr.getBoolean(R.styleable.ShapeTextView_stv_gradient_enable, false)
        if (mGradientEnable) {
            mGradientColors[0] =
                attr.getColor(R.styleable.ShapeTextView_stv_gradient_start, DEFAULT_SOLID_COLOR)
            mGradientColors[1] =
                attr.getColor(
                    R.styleable.ShapeTextView_stv_unusable_gradient_end,
                    DEFAULT_SOLID_COLOR
                )
            mUnusableGradientColors[0] =
                attr.getColor(R.styleable.ShapeTextView_stv_gradient_start, DEFAULT_SOLID_COLOR)
            mUnusableGradientColors[1] =
                attr.getColor(
                    R.styleable.ShapeTextView_stv_unusable_gradient_end,
                    DEFAULT_SOLID_COLOR
                )
            mAngle = attr.getInt(R.styleable.ShapeTextView_stv_gradient_angle, 0)
        }
        mDashGapWidth =
            attr.getDimension(R.styleable.ShapeTextView_stv_dash_gap, mDashWidth)
        mDashWidth =
            attr.getDimension(R.styleable.ShapeTextView_stv_dash_width, mDashWidth)
        mUnusableTextColor =
            attr.getColor(R.styleable.ShapeTextView_stv_unusable_textColor, textColors.defaultColor)
        attr.recycle()
    }

    private fun getGradientOrientation(mAngle: Int): GradientDrawable.Orientation {
        return when (mAngle) {
            TOP_BOTTOM -> GradientDrawable.Orientation.TOP_BOTTOM
            TR_BL -> GradientDrawable.Orientation.TR_BL
            RIGHT_LEFT -> GradientDrawable.Orientation.RIGHT_LEFT
            BR_TL -> GradientDrawable.Orientation.BR_TL
            BOTTOM_TOP -> GradientDrawable.Orientation.BOTTOM_TOP
            BL_TR -> GradientDrawable.Orientation.BL_TR
            LEFT_RIGHT -> GradientDrawable.Orientation.LEFT_RIGHT
            TL_BR -> GradientDrawable.Orientation.TL_BR
            else -> GradientDrawable.Orientation.LEFT_RIGHT
        }
    }

    override fun refreshDrawableState() {
        setEnableStateDrawable(isEnabled)
        super.refreshDrawableState()
    }

    private fun setEnableStateDrawable(isEnabled: Boolean) {
        mGradientDrawable = GradientDrawable()
        if (mGradientEnable) {
            mGradientDrawable.colors = if (isEnabled) {
                mGradientColors
            } else {
                mUnusableGradientColors
            }
            if (mAngle > 0) {
                mGradientDrawable.orientation = getGradientOrientation(mAngle)
            }
        } else {
            mGradientDrawable.setColor(
                if (isEnabled) {
                    mSolidColor
                } else {
                    mUnusableSolidColor
                }
            )
        }
        if (isEnabled) {
            setTextColor(textColors.defaultColor)
        } else {
            setTextColor(mUnusableTextColor)
        }
        val borderColor = if (isEnabled) {
            mBorderColor
        } else {
            mUnusableBorderColor
        }
        mGradientDrawable.setStroke(mBorderWidth, borderColor, mDashWidth, mDashGapWidth)
        mGradientDrawable.cornerRadii = mCornerRadiiArrayList
        background = mGradientDrawable
    }
}