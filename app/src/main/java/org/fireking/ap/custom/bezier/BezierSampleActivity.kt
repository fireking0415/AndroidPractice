package org.fireking.ap.custom.bezier

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_bezier_sample.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class BezierSampleActivity : AppCompatActivity() {

    companion object {

        @JvmStatic
        fun startActivity(context: Context) {
            context.startActivity(context.intentFor<BezierSampleActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bezier_sample)

        btnStart.setOnClickListener {
            bezierView.startAnimation()
        }
    }
}
