package org.fireking.basic.viewgroup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.R
import org.fireking.ap.databinding.ActivityTextAndViewAlignBinding
import org.fireking.library.kotlin.ext.intentFor

class TextAndViewAlignActivity : AppCompatActivity() {

    private var viewBinding: ActivityTextAndViewAlignBinding? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<TextAndViewAlignActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTextAndViewAlignBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        viewBinding?.textAndViewAlignLayout?.setAlignTextAndView(
            "淘宝淘宝淘宝淘宝淘宝淘宝淘宝淘宝淘宝",
            R.mipmap.ic_ks_ad
        )
    }
}
