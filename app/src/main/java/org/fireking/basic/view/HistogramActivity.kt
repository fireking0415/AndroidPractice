package org.fireking.basic.view

import android.content.Context
import org.fireking.ap.databinding.ActivityHistogramBinding
import org.fireking.base.BaseActivity
import org.fireking.library.kotlin.ext.intentFor

class HistogramActivity : BaseActivity<ActivityHistogramBinding>() {

    companion object {

        @JvmStatic
        fun startActivity(context: Context) {
            context.startActivity(context.intentFor<HistogramActivity>())
        }
    }

    override fun initView() {
    }
}
