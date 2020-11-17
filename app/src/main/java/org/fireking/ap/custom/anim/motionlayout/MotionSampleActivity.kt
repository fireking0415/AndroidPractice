package org.fireking.ap.custom.anim.motionlayout

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_motion_sample2.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class MotionSampleActivity : AppCompatActivity() {
    companion object {

        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<MotionSampleActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_sample2)

        btnV1.setOnClickListener {
            MotionLayoutV1Activity.start(this)
        }

        btnV2.setOnClickListener {
            MotionLayoutV2Activity.start(this)
        }

        btnV3.setOnClickListener {
            MotionLayoutV3Activity.start(this)
        }

        btnV4.setOnClickListener {
            MotionLayoutV4Activity.start(this)
        }
    }
}