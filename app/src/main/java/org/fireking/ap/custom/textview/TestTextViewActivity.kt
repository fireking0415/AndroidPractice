package org.fireking.ap.custom.textview

import android.content.Context
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.custom.textview.custom.SpanExtTagHandler
import org.fireking.ap.databinding.ActivityTestTextViewBinding
import org.jetbrains.anko.intentFor

class TestTextViewActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityTestTextViewBinding

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<TestTextViewActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTestTextViewBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.btnClearBorder.setOnClickListener {
            TextViewClearBorderActivity.start(this)
        }

        viewBinding.btnTestFont.setOnClickListener {
            TestFontActivity.start(this)
        }

        val result = "<spanExt>攀钢钒钛所属行业为\n" +
                "<spanExt style=\"color:#333333; font-weight:bold; font-size:18px;\">其他采掘</spanExt>；\n" +
                "</spanExt>\n" +
                "<br/>\n" +
                "<spanExt>当日攀钢钒钛行情整体表现<span style=\"color:green; font-weight:bold;\">弱于</spanExt>所属行业表现；</spanExt>\n" +
                "<br/>\n" +
                "<spanExt>攀钢钒钛所属概念中<spanExt style=\"color:#333333; font-weight:bold; \">有色金属</spanExt>表现相对优异；</spanExt>\n" +
                "<br/>\n" +
                "<spanExt>其涨跌幅在有色金属中位列<spanExt style=\"color:#F43737; font-weight:bold; \">81</spanExt>/<spanExt style=\"color:black; font-weight:bold; \">122</spanExt>。</spanExt>"
        viewBinding.tvTest.text = Html.fromHtml(result, null, SpanExtTagHandler(this, null))
    }
}