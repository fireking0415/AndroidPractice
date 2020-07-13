package org.fireking.ap.custom.viewgroup

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_text_and_view_align.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class TextAndViewAlignActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<TextAndViewAlignActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_and_view_align)

        textAndViewAlignLayout.setAlignTextAndView("淘宝淘宝淘宝淘宝淘宝淘宝淘宝淘宝淘宝", R.mipmap.ic_ks_ad)
    }
}
