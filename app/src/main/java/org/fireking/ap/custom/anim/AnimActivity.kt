package org.fireking.ap.custom.anim

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.custom.anim.motionlayout.MotionSampleActivity
import org.fireking.ap.databinding.ActivityAnimBinding
import org.jetbrains.anko.intentFor

class AnimActivity : AppCompatActivity() {

    private var viewBinding: ActivityAnimBinding? = null

    companion object {

        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<AnimActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityAnimBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        viewBinding?.btnMotionLayout?.setOnClickListener {
            MotionSampleActivity.start(this)
        }
    }
}
