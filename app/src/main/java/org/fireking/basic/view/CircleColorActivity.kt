package org.fireking.basic.view

import android.content.Context
import org.fireking.ap.databinding.ActivityCircleColorBinding
import org.fireking.base.BaseActivity
import org.fireking.library.kotlin.ext.intentFor

class CircleColorActivity : BaseActivity<ActivityCircleColorBinding>() {

    companion object {

        @JvmStatic
        fun startActivity(context: Context) {
            context.startActivity(context.intentFor<CircleColorActivity>())
        }
    }

    override fun initView() {

    }
}
