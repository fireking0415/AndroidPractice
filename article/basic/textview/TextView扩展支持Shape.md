![在这里插入图片描述](https://img-blog.csdnimg.cn/2021011220272880.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dhbmdnYW5nNTE0MjYwNjYz,size_16,color_FFFFFF,t_70)

老规矩，先放个效果图，吸引一波火力。

相信这个效果会是你满意的效果吧，下面简单说一下这个功能使用到的主要技术点。

* 自定义View属性实现(本文章不做说明)
* GradientDrawable

下面说明一下`GradientDrawable`的常规参数和使用

`GradientDrawable`和`ShapeDrawable`很说相似，你对于这两个drawable一定是很熟悉的，只是你可能一般不会使用代码书写而已。

* GradientDrawable使用xml方式
```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">

    <corners android:radius="8dp" />

    <solid android:color="@android:color/white" />

    <stroke
        android:color="#EEEEEE"
        android:dashWidth="0.5dp"
        android:dashGap="2dip" />
</shape>
```
* ShapeDrawable使用xml方式
```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">

    <corners android:radius="8dp" />

    <solid android:color="@android:color/white" />

    <gradient
        android:angle="90"
        android:endColor="#334455"
        android:gradientRadius="5dp"
        android:startColor="#F14400" />

    <stroke
        android:color="#EEEEEE"
        android:dashWidth="0.5dp"
        android:dashGap="2dip" />
</shape>
```

是不是有种恍然大悟的感觉，其实这里只是使用了代码的方式实现xml相同的功能而已，因为使用xml的话，各种样式我们都要写一个
会导致出现一大批的`shape.xml`文件，使用自定义方式，就可以直接通过属性控制，更加方便书写。

* GradientDrawable使用代码方式实现

```kotlin
val mGradientDrawable = GradientDrawable()
//设置颜色，对应solid
mGradientDrawable.setColor()
//设置多个颜色，用于支持gradient方式绘制渐变
mGradientDrawable.setColors()
// 设置stroke
mGradientDrawable.setStroke(mBorderWidth, borderColor, mDashWidth, mDashGapWidth)
//设置圆角，支持每个角度进行设置
mGradientDrawable.cornerRadii = mCornerRadiiArrayList
//设置渐变角度
mGradientDrawable.orientation = GradientDrawable.Orientation.xxx
```
渐变角度设置`GradientDrawable.Orientation`对应方式为：
```
//从上到下
TOP_BOTTOM -> GradientDrawable.Orientation.TOP_BOTTOM
//右上到左下
TR_BL -> GradientDrawable.Orientation.TR_BL
//从右到左
RIGHT_LEFT -> GradientDrawable.Orientation.RIGHT_LEFT
//从右下到左上
BR_TL -> GradientDrawable.Orientation.BR_TL
//从下到上
BOTTOM_TOP -> GradientDrawable.Orientation.BOTTOM_TOP
//从左下到右上
BL_TR -> GradientDrawable.Orientation.BL_TR
//从左到右
LEFT_RIGHT -> GradientDrawable.Orientation.LEFT_RIGHT
//从左上到右下
TL_BR -> GradientDrawable.Orientation.TL_BR
//从左到右
LEFT_RIGHT -> GradientDrawable.Orientation.LEFT_RIGHT
```
对于设置圆角这里需要注意，这边是传入一个`Float`类型的集合，集合大小为`8`，集合对应方式如下
```kotlin
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
```
使用 `GradientDrawable`
```
background = mGradientDrawable
```
那么接下来就上代码了。由于没啥难度，所以直接放代码。
```kotlin
package org.fireking.ap.custom.textview.custom

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
```

使用到的资源文件

```xml
<declare-styleable name="ShapeTextView">
        <!--文字颜色(不可用)-->
        <attr name="stv_unusable_textColor" format="color" />
        <!--描边宽度-->
        <attr name="stv_width" format="dimension" />
        <!--填充色-->
        <attr name="stv_solid" format="color" />
        <!--填充色（不可用）-->
        <attr name="stv_unusable_solid" format="color" />
        <!--边框颜色-->
        <attr name="stv_border_color" format="color" />
        <!--边框颜色(不可用)-->
        <attr name="stv_unusable_border_color" format="color" />
        <!--圆角-->
        <attr name="stv_corners" format="dimension" />
        <!--圆角-左上角-->
        <attr name="stv_topLeft_corners" format="dimension" />
        <!--圆角-右上角-->
        <attr name="stv_topRight_corners" format="dimension" />
        <!--圆角-左下角-->
        <attr name="stv_bottomLeft_corners" format="dimension" />
        <!--圆角-右下角-->
        <attr name="stv_bottomRight_corners" format="dimension" />
        <!--是否支持渐变-->
        <attr name="stv_gradient_enable" format="boolean" />
        <!--渐变开始颜色-->
        <attr name="stv_gradient_start" format="color" />
        <!--渐变开始颜色(不可用)-->
        <attr name="stv_unusable_gradient_start" format="color" />
        <!--渐变结束颜色-->
        <attr name="stv_gradient_end" format="color" />
        <!--渐变结束颜色(不可用)-->
        <attr name="stv_unusable_gradient_end" format="color" />
        <!--渐变角度-->
        <attr name="stv_gradient_angle">
            <enum name="TOP_BOTTOM" value="1" />
            <enum name="TR_BL" value="2" />
            <enum name="RIGHT_LEFT" value="3" />
            <enum name="BR_TL" value="4" />
            <enum name="BOTTOM_TOP" value="5" />
            <enum name="BL_TR" value="6" />
            <enum name="LEFT_RIGHT" value="7" />
            <enum name="TL_BR" value="8" />
        </attr>
        <!--虚线模式间距-->
        <attr name="stv_dash_gap" format="dimension" />
        <!--虚线模式宽度-->
        <attr name="stv_dash_width" format="dimension" />
    </declare-styleable>
```

好了就到这里了，see you ！～