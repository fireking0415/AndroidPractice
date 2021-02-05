package org.fireking.basic.textview

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.text.Html
import android.util.Log
import android.util.TypedValue
import org.fireking.ap.databinding.NewActivityTestTextViewBinding
import org.fireking.base.BaseActivity
import org.fireking.basic.textview.html.SpanExtTagHandler
import org.fireking.library.kotlin.ext.intentFor

class TextViewModuleActivity : BaseActivity<NewActivityTestTextViewBinding>() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<TextViewModuleActivity>())
        }
    }

    override fun initView() {
        title = "TextView"
        bindView {
            val paint = Paint(Paint.ANTI_ALIAS_FLAG)
            tvSize10.post {
                paint.textSize = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP,
                    10F,
                    resources.displayMetrics
                )
                val rect = Rect()
                paint.getTextBounds(tvSize10.text.toString(), 0, tvSize10.text.length, rect)
                Log.e("info", "size10->" + tvSize10.height + ", " + rect.height())
            }
            tvSize12.post {
                paint.textSize = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP,
                    12F,
                    resources.displayMetrics
                )
                val rect = Rect()
                paint.getTextBounds(tvSize12.text.toString(), 0, tvSize12.text.length, rect)
                Log.e("info", "size12->" + tvSize12.height + ", " + rect.height())
            }
            tvSize14.post {
                paint.textSize = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP,
                    14F,
                    resources.displayMetrics
                )
                val rect = Rect()
                paint.getTextBounds(tvSize14.text.toString(), 0, tvSize14.text.length, rect)
                Log.e("info", "size14->" + tvSize14.height + ", " + rect.height())
            }
            tvSize16.post {
                paint.textSize = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP,
                    16F,
                    resources.displayMetrics
                )
                val rect = Rect()
                paint.getTextBounds(tvSize16.text.toString(), 0, tvSize16.text.length, rect)
                Log.e("info", "size16->" + tvSize16.height + ", " + rect.height())
            }
            tvSize18.post {
                paint.textSize = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP,
                    18F,
                    resources.displayMetrics
                )
                val rect = Rect()
                paint.getTextBounds(tvSize18.text.toString(), 0, tvSize18.text.length, rect)
                Log.e("info", "size18->" + tvSize18.height + ", " + rect.height())
            }
            tvSize20.post {
                paint.textSize = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP,
                    20F,
                    resources.displayMetrics
                )
                val rect = Rect()
                paint.getTextBounds(tvSize20.text.toString(), 0, tvSize20.text.length, rect)
                Log.e("info", "size20->" + tvSize20.height + ", " + rect.height())
            }
            val result = "<spanExt>攀钢钒钛所属行业为\n" +
                    "<spanExt style=\"color:#333333; font-weight:bold; font-size:18px;\">其他采掘</spanExt>；\n" +
                    "</spanExt>\n" +
                    "<br/>\n" +
                    "<spanExt>当日攀钢钒钛行情整体表现<spanExt style=\"color:green; font-weight:bold;\">弱于</spanExt>所属行业表现；</spanExt>\n" +
                    "<br/>\n" +
                    "<spanExt>攀钢钒钛所属概念中<spanExt style=\"color:#333333; font-weight:bold; \">有色金属</spanExt>表现相对优异；</spanExt>\n" +
                    "<br/>\n" +
                    "<spanExt>其涨跌幅在有色金属中位列<spanExt style=\"color:#F43737; font-weight:bold; \">81</spanExt>/<spanExt style=\"color:black; font-weight:bold; \">122</spanExt>。</spanExt>"
            tvTest.text =
                Html.fromHtml(
                    result, null,
                    SpanExtTagHandler(
                        this@TextViewModuleActivity,
                        null
                    )
                )
            tvSize20.setSpannableText(
                arrayListOf(
                    SpanEntity(
                        // 文字颜色
                        color = Color.parseColor("#F14400"),
                        // 文字内容
                        content = "我的内容",
                        // 文字样式
                        typeface = Typeface.BOLD,
                        // 文字是否支持点击
                        clickable = true,
                        // 文字点击事件
                        clickEvent = {}
                    )
                )
            )
        }
    }
}