package org.fireking.ap.custom.anim.motionlayout

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.databinding.ActivityMotionSample2Binding
import org.jetbrains.anko.intentFor

class MotionSampleActivity : AppCompatActivity() {

    private var viewBinding: ActivityMotionSample2Binding? = null

    companion object {

        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<MotionSampleActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMotionSample2Binding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        viewBinding?.btnV1?.setOnClickListener {
            MotionLayoutV1Activity.start(this)
        }

        viewBinding?.btnV2?.setOnClickListener {
            MotionLayoutV2Activity.start(this)
        }

        viewBinding?.btnV3?.setOnClickListener {
            MotionLayoutV3Activity.start(this)
        }

        viewBinding?.btnV4?.setOnClickListener {
            MotionLayoutV4Activity.start(this)
        }
    }
}