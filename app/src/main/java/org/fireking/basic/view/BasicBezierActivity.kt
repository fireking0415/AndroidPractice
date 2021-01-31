package org.fireking.basic.view

import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.fireking.ap.R
import org.fireking.library.kotlin.ext.intentFor

class BasicBezierActivity : AppCompatActivity() {

    companion object {

        @JvmStatic
        fun startActivity(context: Context) {
            context.startActivity(context.intentFor<BasicBezierActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_bezier)

        findViewById<Button>(R.id.btnDoubleWave).setOnClickListener {
            DoubleWaveActivity.startActivity(this@BasicBezierActivity)
        }

        findViewById<Button>(R.id.btnSinusoidalWave).setOnClickListener {
            SinusoidalWaveActivity.startActivity(this@BasicBezierActivity)
        }

        findViewById<Button>(R.id.btnWater).setOnClickListener {
            BezierSampleActivity.startActivity(this@BasicBezierActivity)
        }

        findViewById<Button>(R.id.btnPathMeasure).setOnClickListener {
            PathMeasureActivity.startActivity(this@BasicBezierActivity)
        }
    }
}
