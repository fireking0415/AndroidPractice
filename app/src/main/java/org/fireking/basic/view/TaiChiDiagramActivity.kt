package org.fireking.basic.view

import android.content.Context
import org.fireking.ap.databinding.ActivityTaiChiDiagramBinding
import org.fireking.base.BaseActivity
import org.fireking.library.kotlin.ext.intentFor

class TaiChiDiagramActivity : BaseActivity<ActivityTaiChiDiagramBinding>() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<TaiChiDiagramActivity>())
        }
    }

    override fun initView() {

    }
}