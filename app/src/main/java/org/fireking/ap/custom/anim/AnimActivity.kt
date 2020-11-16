package org.fireking.ap.custom.anim

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_anim.*
import org.fireking.ap.R
import org.fireking.ap.custom.anim.motionlayout.MotionLayoutV1Activity
import org.fireking.ap.custom.anim.motionlayout.MotionSampleActivity
import org.jetbrains.anko.intentFor

class AnimActivity : AppCompatActivity() {

    companion object {

        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<AnimActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim)

        btnMotionLayout.setOnClickListener {
            MotionSampleActivity.start(this)
        }
    }
}
