package org.fireking.basic.animator

import android.content.Context
import org.fireking.ap.databinding.ActivityAnimBinding
import org.fireking.base.BaseActivity
import org.fireking.basic.animator.motionlayout.MotionSampleActivity
import org.fireking.library.kotlin.ext.intentFor

class AnimActivity : BaseActivity<ActivityAnimBinding>() {

    companion object {

        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<AnimActivity>())
        }
    }

    override fun initView() {
        title = "Animator"

        bindView {

            btnMotionLayout.setOnClickListener {
                MotionSampleActivity.start(this@AnimActivity)
            }
        }
    }
}
