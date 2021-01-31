package org.fireking.basic.view

import android.content.Context
import org.fireking.ap.databinding.ActivityDrawCubicLineBinding
import org.fireking.base.BaseActivity
import org.fireking.library.kotlin.ext.intentFor

class DrawCubicLineActivity : BaseActivity<ActivityDrawCubicLineBinding>() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<DrawCubicLineActivity>())
        }
    }

    override fun initView() {
    }
}