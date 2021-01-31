package org.fireking.basic.view

import android.content.Context
import org.fireking.ap.databinding.ActivityPathTestV1Binding
import org.fireking.base.BaseActivity
import org.fireking.library.kotlin.ext.intentFor

class PathTestV1Activity : BaseActivity<ActivityPathTestV1Binding>() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<PathTestV1Activity>())
        }
    }

    override fun initView() {

    }
}