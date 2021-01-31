package org.fireking.basic.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.databinding.ActivityBezierSampleBinding
import org.fireking.library.kotlin.ext.intentFor

class BezierSampleActivity : AppCompatActivity() {

    private var viewBinding: ActivityBezierSampleBinding? = null

    companion object {

        @JvmStatic
        fun startActivity(context: Context) {
            context.startActivity(context.intentFor<BezierSampleActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityBezierSampleBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        viewBinding?.btnStart?.setOnClickListener {
            viewBinding?.bezierView?.startAnimation()
        }
    }
}
